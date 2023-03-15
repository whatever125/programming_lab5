package sources.IOHandlers;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import sources.MovieCollection;
import sources.exceptions.InvalidFileDataException;
import sources.models.*;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.time.format.DateTimeParseException;
import java.util.Arrays;

/**
 * A class for reading XML files and creating a MovieCollection object from the data.
 */
public class XMLFileReader implements MovieCollectionFileReader {
    private final String path;

    /**
     * Constructs an XMLFileReader object with the given file path.
     * @param path the file path of the XML file to be read
     */
    public XMLFileReader(String path) {
        this.path = path;
    }

    /**
     * Reads the XML file and creates a MovieCollection object from the data.
     * @return a MovieCollection object created from the XML file data
     * @throws InvalidFileDataException if the data in the file is invalid or cannot be parsed
     */
    @Override
    public MovieCollection read() throws InvalidFileDataException {
        try {
            MovieCollection movieCollection = new MovieCollection();

            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();

            File file = new File(path);
            if (file.length() == 0) {
                movieCollection.setCreationDate(ZonedDateTime.now());
                return movieCollection;
            }

            Document document = builder.parse(file);
            document.getDocumentElement().normalize();

            String collectionCreationDateInput = document.getDocumentElement().getAttribute("creationDate");
            ZonedDateTime collectionCreationDate = ZonedDateTime.parse(collectionCreationDateInput);

            NodeList movieElements = document.getDocumentElement().getElementsByTagName("movie");
            for (int i = 0; i < movieElements.getLength(); i++) {
                Element movieElement = (Element) movieElements.item(i);

                String idInput = movieElement.getElementsByTagName("id").item(0).getTextContent().trim();
                int id = Integer.parseInt(idInput);
                String movieName = movieElement.getElementsByTagName("name").item(0).getTextContent().trim();

                Element coordinatesInput = (Element) movieElement.getElementsByTagName("coordinates").item(0);
                String xInput = coordinatesInput.getElementsByTagName("x").item(0).getTextContent().trim();
                int x = Integer.parseInt(xInput);
                String yInput = coordinatesInput.getElementsByTagName("y").item(0).getTextContent().trim();
                int y = Integer.parseInt(yInput);
                Coordinates coordinates = new Coordinates(x, y);

                String creationDateInput = movieElement.getElementsByTagName("creationDate").item(0).getTextContent().trim();
                ZonedDateTime creationDate = ZonedDateTime.parse(creationDateInput);
                String oscarsCountInput = movieElement.getElementsByTagName("oscarsCount").item(0).getTextContent().trim();
                long oscarsCount = Long.parseLong(oscarsCountInput);
                String genreInput = movieElement.getElementsByTagName("genre").item(0).getTextContent().trim();
                MovieGenre movieGenre = MovieGenre.valueOf(genreInput);
                String mpaaRatingInput = movieElement.getElementsByTagName("mpaaRating").item(0).getTextContent().trim();
                MpaaRating mpaaRating = MpaaRating.valueOf(mpaaRatingInput);

                Element directorInput = (Element) movieElement.getElementsByTagName("director").item(0);
                String directorNameInput = directorInput.getElementsByTagName("name").item(0).getTextContent().trim();
                String birthdayInput = directorInput.getElementsByTagName("birthday").item(0).getTextContent().trim();
                LocalDateTime birthday = LocalDateTime.parse(birthdayInput);
                String weightInput = directorInput.getElementsByTagName("weight").item(0).getTextContent().trim();
                Integer weight = Integer.parseInt(weightInput);
                Node passportIDInput = directorInput.getElementsByTagName("passportID").item(0);
                String passportID = null;
                if (passportIDInput != null && !passportIDInput.getTextContent().trim().equals("")) {
                    passportID = passportIDInput.getTextContent().trim();
                }
                Person director = new Person(directorNameInput, birthday, weight, passportID);

                if (movieCollection.get(id) == null) {
                    Movie movie = new Movie(movieName, coordinates, oscarsCount, movieGenre, mpaaRating, director);
                    movie.setId(id);
                    movie.setCreationDate(creationDate);
                    movieCollection.put(id, movie);
                } else {
                    throw new IllegalArgumentException("! movie id must be unique !");
                }
            }
            movieCollection.setCreationDate(collectionCreationDate);
            return movieCollection;
        } catch (NullPointerException | ParserConfigurationException | IOException | SAXException | IllegalArgumentException |
                 DateTimeParseException e) {
            System.out.println(e.getClass());
            System.out.println(Arrays.toString(e.getStackTrace()));
            throw new InvalidFileDataException(e.getMessage());
        }
    }
}
