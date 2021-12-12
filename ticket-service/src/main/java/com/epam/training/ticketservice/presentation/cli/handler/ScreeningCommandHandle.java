package com.epam.training.ticketservice.presentation.cli.handler;

import com.epam.training.ticketservice.model.Movie;
import com.epam.training.ticketservice.model.Room;
import com.epam.training.ticketservice.model.Screening;
import com.epam.training.ticketservice.service.AccountLoginService;
import com.epam.training.ticketservice.service.MovieService;
import com.epam.training.ticketservice.service.RoomService;
import com.epam.training.ticketservice.service.ScreeningService;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@ShellComponent
public class ScreeningCommandHandle {

    private final ScreeningService screeningService;
    private final AccountLoginService accountLoginService;
    private final MovieService movieService;
    private final RoomService roomService;

    private final SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd hh:mm");

    public ScreeningCommandHandle(ScreeningService screeningService, AccountLoginService accountLoginService, MovieService movieService, RoomService roomService) {
        this.screeningService = screeningService;
        this.accountLoginService = accountLoginService;
        this.movieService = movieService;
        this.roomService = roomService;
    }

    @ShellMethod(value = "Screening list", key = "list screenings")
    public String listScreens() {
        List<Screening> screenings = screeningService.getAllScreening();
        Collections.sort(screenings);
        if (!screenings.isEmpty()) {
            StringBuilder list = new StringBuilder();
            for (Screening s : screenings) {
                list.append(s.getTitle() + " (" + s.getGenreMovie() + ", " + movieService.getMovie(s.getTitle()).getMovieLengthMin() + " minutes), screened in room " + s.getRoomName() + ", at " + s.getScreenTime() + "\n");
            }
            return list + "";
        }
        return "There are no screenings";
    }

    @ShellMethod(value = "Screening creator", key = "create screening")
    public String createScreening(String title, String room, String screenTime) throws ParseException {
        String result = "You are not admin";
        if (accountLoginService.accountLoggedIn()) {

            List<Movie> movies = movieService.getAllMovie();
            List<Room> rooms = roomService.getAllRoom();
            List<Screening> screenings = screeningService.getAllScreening();
            List<Screening> filteredByRooms = screenings.stream().filter((screening) -> screening.getRoomName().equals(room)).collect(Collectors.toList());

            long breakMillis = 600000;
            Date inputDate = formatter.parse(screenTime);

            for (Screening sc : filteredByRooms) {
                String screenTime1 = sc.getScreenTime();
                Movie movie = movieService.getMovie(sc.getTitle());
                long movieInMillis = movie.getMovieLengthMin() * 60000L;
                Date startDate = formatter.parse(screenTime1);
                Date endDate = new Date(startDate.getTime() + movieInMillis);
                Date endDateWithBreak = new Date(endDate.getTime() + breakMillis);

                if (inputDate.after(startDate) && inputDate.before(endDate) || startDate.equals(inputDate)) {
                    return "There is an overlapping screening";
                }
                else if (inputDate.after(endDate) && inputDate.before(endDateWithBreak) || endDate.equals(inputDate) ) {
                    return "This would start in the break period after another screening in this room";
                }
            }
            for (Movie movie : movies) {
                if (movie.getTitle().equals(title)) {
                    for (Room value : rooms) {
                        if (value.getName().equals(room)) {
                            screeningService.createScreening(title, movie.getGenre(), room, screenTime);
                            return "Screening created: " + title + " " + room + " " + screenTime;
                        }
                    }
                }
            }
            result = "There is no room or movie.";
        }
        return result;
    }

    @ShellMethod(value = "Screening deleter", key = "delete screening")
    public String deleteScreening(String title, String room, String screenTime) {
        if (accountLoginService.accountLoggedIn()) {
            Screening screeningDelete = screeningService.getScreening(title, room, screenTime);
            screeningService.deleteScreening(screeningDelete);
            return "Screening deleted: " + title + " " + room + " " + screenTime;
        }
        return "You are not admin";
    }
}
