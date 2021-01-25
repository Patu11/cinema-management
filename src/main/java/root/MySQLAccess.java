package root;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MySQLAccess {
    private final String USER = "root";
    private final String PASSWORD = "root";
    private final String LINK = "jdbc:mysql://localhost:3306/cinema";

    private Connection connection;
    private Statement statement;
    private Statement statement2;
    private PreparedStatement preparedStatement;
    private ResultSet resultSet;

    public MySQLAccess() throws SQLException, ClassNotFoundException {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            connection = DriverManager.getConnection(LINK, USER, PASSWORD);

            statement = connection.createStatement();
            statement2 = connection.createStatement();

        } catch (Exception e) {
            throw e;
        }
    }

    public void insertClient(Client client) throws SQLException {
        String sql = "INSERT INTO client(first_name, last_name, birth_date) VALUES(?,?,?)";

        PreparedStatement preparedStatement = connection.prepareStatement(sql);

        preparedStatement.setString(1, client.getFirstName());
        preparedStatement.setString(2, client.getLastName());
        preparedStatement.setDate(3, java.sql.Date.valueOf(client.getBirthDate().toString()));

        preparedStatement.executeUpdate();
    }

    public void insertHall(Hall hall) throws SQLException {
        String sql = "INSERT INTO hall(seats_number, available_seats) VALUES(?,?)";

        PreparedStatement preparedStatement = connection.prepareStatement(sql);

        preparedStatement.setInt(1, hall.getSeatsNumber());
        preparedStatement.setInt(2, hall.getAvailableSeats());

        preparedStatement.executeUpdate();
    }

    public void insertMovie(Movie movie) throws SQLException {
        String sql = "INSERT INTO movie(title, age_cat, length, genre,price) VALUES(?,?,?,?,?)";

        PreparedStatement preparedStatement = connection.prepareStatement(sql);

        preparedStatement.setString(1, movie.getTitle());
        preparedStatement.setInt(2, movie.getAgeCategory());
        preparedStatement.setInt(3, movie.getLength());
        preparedStatement.setString(4, movie.getGenre());
        preparedStatement.setDouble(5, movie.getPrice());

        preparedStatement.executeUpdate();
    }

    public void deleteMovieByTitle(String title) throws SQLException {
        String sql = "DELETE FROM movie where title = ?";

        PreparedStatement preparedStatement = connection.prepareStatement(sql);

        preparedStatement.setString(1, title);

        preparedStatement.executeUpdate();
    }

    public void deleteHallByNumber(int number) throws SQLException {
        String sql = "DELETE FROM hall where hall_id = ?";

        PreparedStatement preparedStatement = connection.prepareStatement(sql);

        preparedStatement.setInt(1, number);

        preparedStatement.executeUpdate();
    }

    public int getMovieIdByTitle(String title) throws SQLException {
        String sql = "SELECT movie_id FROM movie WHERE title = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1, title);

        resultSet = preparedStatement.executeQuery();

        while (resultSet.next()) {
            int id = resultSet.getInt("movie_id");
            return id;
        }
        return -1;
    }

    public int getClientIdByNameAndSurname(String name, String surname) throws SQLException {
        String sql = "SELECT client_id FROM client WHERE first_name = ? AND last_name = ?";
//        System.out.println("SELECT client_id FROM client WHERE first_name = " + name + " AND last_name = " + surname);
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1, name);
        preparedStatement.setString(2, surname);

        resultSet = preparedStatement.executeQuery();

        while (resultSet.next()) {
            int id = resultSet.getInt("client_id");
            return id;
        }
        return -1;
    }

    public void insertReservation(Reservation reservation) throws SQLException {
        String sql = "INSERT INTO reservation(hall_id, movie_id, client_id, res_date, seat_number,price) VALUES(?,?,?,?,?,?)";


        PreparedStatement preparedStatement = connection.prepareStatement(sql);

        int movieId = this.getMovieIdByTitle(reservation.getMovie().getTitle());
        int clientId = this.getClientIdByNameAndSurname(reservation.getClient().getFirstName(), reservation.getClient().getLastName());

        preparedStatement.setInt(1, reservation.getCinemaHall().getHallNumber());
        preparedStatement.setInt(2, movieId);
        preparedStatement.setInt(3, clientId);
        preparedStatement.setDate(4, (Date) reservation.getDate());
        preparedStatement.setInt(5, reservation.getSeatNumber());
        preparedStatement.setDouble(6, reservation.getPrice());

        preparedStatement.executeUpdate();
    }

    public void addHallAvailableSeatsByNumber(int number, int seats) throws SQLException {
        String sql = "UPDATE hall SET available_seats = available_seats + ? WHERE hall_id = ?";

        PreparedStatement preparedStatement = connection.prepareStatement(sql);

        preparedStatement.setInt(1, seats);
        preparedStatement.setInt(2, number);

        preparedStatement.executeUpdate();
    }

    public void updateHallAvailableSeatsByNumber(int number, int seats) throws SQLException {
        String sql = "UPDATE hall SET available_seats = ? WHERE hall_id = ?";

        PreparedStatement preparedStatement = connection.prepareStatement(sql);

        preparedStatement.setInt(1, seats);
        preparedStatement.setInt(2, number);

        preparedStatement.executeUpdate();
    }

    public List<Hall> getAllHalls() throws SQLException {
        resultSet = statement.executeQuery("SELECT * FROM hall");
        ResultSet temp;
        List<Hall> list = new ArrayList<>();

        while (resultSet.next()) {
            int number = resultSet.getInt("hall_id");
            int seats = resultSet.getInt("seats_number");
            int available = resultSet.getInt("available_seats");

            Hall h = new Hall(number, seats, available);
            list.add(h);
        }
        return list;
    }

    public Hall getHallByNumber(int n) throws SQLException {
        String sql = "SELECT * FROM hall WHERE hall_id = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1, String.valueOf(n));

        resultSet = preparedStatement.executeQuery();

        Hall h = null;
        while (resultSet.next()) {
            int number = resultSet.getInt("hall_id");
            int seats = resultSet.getInt("seats_number");
            int available = resultSet.getInt("available_seats");

            h = new Hall(number, seats, available);
        }
        return h;
    }

    public List<Movie> getAllMovies() throws SQLException {
        resultSet = statement.executeQuery("SELECT * FROM movie");
        ResultSet temp;
        List<Movie> list = new ArrayList<>();

        while (resultSet.next()) {
            String title = resultSet.getString("title");
            int ageCategory = resultSet.getInt("age_cat");
            int length = resultSet.getInt("length");
            String genre = resultSet.getString("genre");
            double price = resultSet.getDouble("price");

            Movie m = new Movie(title, ageCategory, length, genre, price);
            list.add(m);
        }
        return list;
    }

    public Movie getMovieByTitle(String t) throws SQLException {
        String sql = "SELECT * FROM movie WHERE title = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1, t);

        resultSet = preparedStatement.executeQuery();

        Movie m = null;
        while (resultSet.next()) {
            String title = resultSet.getString("title");
            int ageCategory = resultSet.getInt("age_cat");
            int length = resultSet.getInt("length");
            String genre = resultSet.getString("genre");
            double price = resultSet.getDouble("price");

            m = new Movie(title, ageCategory, length, genre, price);
        }
        return m;
    }

    public List<Client> getAllClients() throws SQLException {
        resultSet = statement.executeQuery("SELECT * FROM client");
        ResultSet temp;
        List<Client> list = new ArrayList<>();

        while (resultSet.next()) {
            String firstName = resultSet.getString("first_name");
            String lastName = resultSet.getString("last_name");
            Date birtDate = resultSet.getDate("birth_date");

            Client c = new Client(firstName, lastName, birtDate);
            list.add(c);
        }
        return list;
    }

    public Client getClientByNames(String first, String last) throws SQLException {
        String sql = "SELECT * FROM client WHERE first_name = ? AND last_name = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1, first);
        preparedStatement.setString(2, last);

        resultSet = preparedStatement.executeQuery();

        Client c = null;
        while (resultSet.next()) {
            String fname = resultSet.getString("first_name");
            String lname = resultSet.getString("last_name");
            Date d = Date.valueOf(resultSet.getString("birth_date"));

            c = new Client(fname, lname, d);
        }
        return c;
    }

    public List<Reservation> getAllReservations() throws SQLException {
        resultSet = statement.executeQuery("SELECT * FROM reservation");
        ResultSet temp;
        List<Reservation> list = new ArrayList<>();
        while (resultSet.next()) {
            int hallId = resultSet.getInt(2);
            int movieId = resultSet.getInt(3);
            int clientId = resultSet.getInt(4);
            Date reservationDate = resultSet.getDate(5);
            int seatNumber = resultSet.getInt(6);
            int price = resultSet.getInt(7);

            temp = statement2.executeQuery("SELECT * FROM hall WHERE hall_id =" + hallId);
            temp.next();
            Hall h = new Hall(temp.getInt(1), temp.getInt(2), temp.getInt(3));

            temp = statement2.executeQuery("SELECT * FROM movie WHERE movie_id =" + movieId);
            temp.next();
            Movie m = new Movie(temp.getString(2), temp.getInt(3), temp.getInt(4), temp.getString(5), temp.getDouble(6));

            temp = statement2.executeQuery("SELECT * FROM client WHERE client_id =" + clientId);
            temp.next();
            Client c = new Client(temp.getString(2), temp.getString(3), temp.getDate(4));

            Reservation r = new Reservation(h, c, m, seatNumber, reservationDate, price);
            list.add(r);
        }
        return list;
    }

    public void closeConnection() throws SQLException {
        connection.close();
    }


}
