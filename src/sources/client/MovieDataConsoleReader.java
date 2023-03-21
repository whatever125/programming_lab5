package sources.client;

import sources.IOHandlers.client.BasicReader;
import sources.exceptions.client.InvalidScriptException;
import sources.exceptions.io.WrongArgumentException;
import sources.models.MovieGenre;
import sources.models.MpaaRating;
import sources.models.helpers.MovieArgumentChecker;
import sources.models.helpers.PersonArgumentChecker;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Objects;

public class MovieDataConsoleReader {
    public static String readMovieName(BasicReader basicReader, boolean inScriptMode) throws InvalidScriptException {
        String movieName = null;
        while (movieName == null) {
            try {
                String input = basicReader.readLine("Enter movie name");
                movieName = input.trim();
                MovieArgumentChecker.checkName(movieName);
            } catch (WrongArgumentException e) {
                movieName = null;
                if (inScriptMode) {
                    throw new InvalidScriptException(e.getMessage());
                } else {
                    System.out.println(e.getMessage());
                }
            }
        }
        return movieName;
    }

    public static Integer readX(BasicReader basicReader, boolean inScriptMode) throws InvalidScriptException {
        Integer x = null;
        boolean xSuccess = false;
        while (!xSuccess) {
            try {
                String input = basicReader.readLine("Enter X coordinate");
                String xInput = input.trim();
                x = Integer.parseInt(xInput);
                xSuccess = true;
            } catch (NumberFormatException e) {
                String errorMessage = "! not an integer !";
                if (inScriptMode) {
                    throw new InvalidScriptException(errorMessage);
                } else {
                    System.out.println(errorMessage);
                }
            }
        }
        return x;
    }

    public static Integer readY(BasicReader basicReader, boolean inScriptMode) throws InvalidScriptException {
        Integer y = null;
        boolean ySuccess = false;
        while (!ySuccess) {
            try {
                String input = basicReader.readLine("Enter Y coordinate");
                String xInput = input.trim();
                y = Integer.parseInt(xInput);
                ySuccess = true;
            } catch (NumberFormatException e) {
                String errorMessage = "! not an integer !";
                if (inScriptMode) {
                    throw new InvalidScriptException(errorMessage);
                } else {
                    System.out.println(errorMessage);
                }
            }
        }
        return y;
    }

    public static long readOscrasCount(BasicReader basicReader, boolean inScriptMode) throws InvalidScriptException {
        long oscarsCount = 0;
        boolean oscarsCountSuccess = false;
        while (!oscarsCountSuccess) {
            try {
                String input = basicReader.readLine("Enter oscars count");
                String oscarsCountInput = input.trim();
                oscarsCount = Long.parseLong(oscarsCountInput);
                MovieArgumentChecker.checkOscarsCount(oscarsCount);
                oscarsCountSuccess = true;
            } catch (NumberFormatException e) {
                String errorMessage = "! not an integer !";
                if (inScriptMode) {
                    throw new InvalidScriptException(errorMessage);
                } else {
                    System.out.println(errorMessage);
                }
            } catch (WrongArgumentException e) {
                String errorMessage = e.getMessage();
                if (inScriptMode) {
                    throw new InvalidScriptException(errorMessage);
                } else {
                    System.out.println(errorMessage);
                }
            }
        }
        return oscarsCount;
    }

    public static MovieGenre readMovieGenre(BasicReader basicReader, boolean inScriptMode) throws InvalidScriptException {
        MovieGenre movieGenre = null;

        StringBuilder message = new StringBuilder("Enter movie genre (");
        for (MovieGenre genre : MovieGenre.values()) {
            message.append(genre.toString());
            message.append("; ");
        }
        message.delete(message.length() - 2, message.length());
        message.append(")");

        while (movieGenre == null) {
            String input = basicReader.readLine(String.valueOf(message));
            String movieGenreInput = input.trim();
            try {
                movieGenre = MovieGenre.valueOf(movieGenreInput.toUpperCase());
            } catch (IllegalArgumentException e) {
                String errorMessage = "! wrong movie genre !";
                if (inScriptMode) {
                    throw new InvalidScriptException(errorMessage);
                } else {
                    System.out.println(errorMessage);
                }
            }
        }
        return movieGenre;
    }

    public static MpaaRating readMpaaRating(BasicReader basicReader, boolean inScriptMode) throws InvalidScriptException {
        MpaaRating mpaaRating = null;

        StringBuilder message = new StringBuilder("Enter MPAA rating (");
        for (MpaaRating rating : MpaaRating.values()) {
            message.append(rating.toString());
            message.append("; ");
        }
        message.delete(message.length() - 2, message.length());
        message.append(")");

        while (mpaaRating == null) {
            String input = basicReader.readLine(String.valueOf(message));
            String mpaaRatingInput = input.trim();
            try {
                mpaaRating = MpaaRating.valueOf(mpaaRatingInput.toUpperCase());
            } catch (IllegalArgumentException e) {
                String errorMessage = "! wrong MPAA rating !";
                if (inScriptMode) {
                    throw new InvalidScriptException(errorMessage);
                } else {
                    System.out.println(errorMessage);
                }
            }
        }
        return mpaaRating;
    }

    public static String readDirectorName(BasicReader basicReader, boolean inScriptMode) throws InvalidScriptException {
        String directorName = null;
        while (directorName == null) {
            try {
                String input = basicReader.readLine("Enter director name");
                directorName = input.trim();
                PersonArgumentChecker.checkName(directorName);
            } catch (WrongArgumentException e) {
                directorName = null;
                if (inScriptMode) {
                    throw new InvalidScriptException(e.getMessage());
                } else {
                    System.out.println(e.getMessage());
                }
            }
        }
        return directorName;
    }

    public static LocalDateTime readBirthday(BasicReader basicReader, boolean inScriptMode) throws InvalidScriptException {
        LocalDateTime birthday = null;
        while (birthday == null) {
            try {
                String input = basicReader.readLine("Enter director birthday in DD.MM.YYYY format");
                String birthdayInput = input.trim();
                birthday = LocalDate.parse(birthdayInput, DateTimeFormatter.ofPattern("dd.MM.yyyy")).atStartOfDay();
            } catch (DateTimeParseException e) {
                String errorMessage = "! wrong date format !";
                if (inScriptMode) {
                    throw new InvalidScriptException(errorMessage);
                } else {
                    System.out.println(errorMessage);
                }
            }
        }
        return birthday;
    }

    public static Integer readWeight(BasicReader basicReader, boolean inScriptMode) throws InvalidScriptException {
        Integer weight = null;
        boolean weightSuccess = false;
        while (!weightSuccess) {
            try {
                String input = basicReader.readLine("Enter director weight");
                String weightInput = input.trim();
                if (!weightInput.equals("")) {
                    weight = Integer.parseInt(weightInput);
                }
                PersonArgumentChecker.checkWeight(weight);
                weightSuccess = true;
            } catch (NumberFormatException e) {
                String errorMessage = "! not an integer !";
                if (inScriptMode) {
                    throw new InvalidScriptException(errorMessage);
                } else {
                    System.out.println(errorMessage);
                }
            } catch (WrongArgumentException e) {
                if (inScriptMode) {
                    throw new InvalidScriptException(e.getMessage());
                } else {
                    System.out.println(e.getMessage());
                }
            }
        }
        return weight;
    }

    public static String readPassportID(BasicReader basicReader, boolean inScriptMode) throws InvalidScriptException {
        boolean passportIDSuccess = false;
        String passportID = null;
        while (!passportIDSuccess) {
            try {
                String input = basicReader.readLine("Enter director passport ID");
                passportID = input.trim();
                if (Objects.equals(passportID, "")) {
                    passportID = null;
                }
                PersonArgumentChecker.checkPassportID(passportID);
                passportIDSuccess = true;
            } catch (WrongArgumentException e) {
                if (inScriptMode) {
                    throw new InvalidScriptException(e.getMessage());
                } else {
                    System.out.println(e.getMessage());
                }
            }
        }
        return passportID;
    }
}
