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


            temp = statement2.executeQuery("SELECT * FROM hall WHERE hall_id =" + hallId);
            temp.next();
            Hall h = new Hall(temp.getInt(1), temp.getInt(2), temp.getInt(3));

            temp = statement2.executeQuery("SELECT * FROM movie WHERE movie_id =" + movieId);
            temp.next();
            Movie m = new Movie(temp.getString(2), temp.getInt(3), temp.getInt(4), temp.getString(5), temp.getDouble(6));

            temp = statement2.executeQuery("SELECT * FROM client WHERE client_id =" + clientId);
            temp.next();
            Client c = new Client(temp.getString(2), temp.getString(3), temp.getDate(4));

            Reservation r = new Reservation(h, c, m, seatNumber, reservationDate);
            list.add(r);
        }
        return list;
    }

    public void closeConnection() throws SQLException {
        connection.close();
    }

    public static void main(String[] args) throws Exception {
        MySQLAccess sql = new MySQLAccess();

//        List<Reservation> reservations = sql.getAllReservations();
//        List<Client> clients = sql.getAllClients();
//        List<Movie> movies = sql.getAllMovies();
//        List<Hall> halls = sql.getAllHalls();
//
//        for (Client c : clients) {
//            System.out.println(c.toString());
//        }
//
//        for (Movie m : movies) {
//            System.out.println(m.toString());
//        }
//
//        for (Hall h : halls) {
//            System.out.println(h.toString());
//        }

//        Movie m = new Movie("The Avengers", 12, 120, "Sci-Fi", 2.55);
//        sql.insertMovie(m);

//        Hall h = new Hall(85, 85);
//        sql.insertHall(h);

        Client c = new Client("Kamil", "Slimak", Date.valueOf("1995-05-01"));
        sql.insertClient(c);

        sql.closeConnection();
    }
}
