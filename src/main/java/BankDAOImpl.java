//import org.eclipse.jetty.server.ConnectionFactory;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class BankDAOImpl implements BankDao {

    @Override
    public Long addAccount(Account a) {
        final String SQL = "insert into Account values (DEFAULT,?,?,?)";
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement statement = conn.prepareStatement(SQL, PreparedStatement.RETURN_GENERATED_KEYS)) {
            statement.setString(1, a.getName());
            statement.setString(2, a.getAddress());
            statement.setString(3, a.getAmount().toString());
            statement.executeUpdate();
            try (ResultSet rs = statement.getGeneratedKeys()) {
                if (rs.next()) {
                    a.setId(rs.getLong(1));
                    return a.getId();
                }
            } catch (SQLException ex) {
                throw new DataAccessException(ex);
            }

        } catch (SQLException ex) {
            throw new DataAccessException(ex);
        }
        throw new DataAccessException();
    }

    @Override
    public Optional<Account> findAccount(Long id) {
        final String SQL = "select * from Account where id = ?";
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement statement = conn.prepareStatement(SQL)) {
            statement.setLong(1, id);
            try (ResultSet rs = statement.executeQuery()) {
                if (rs.next()) {
                    Account user = new Account();
                    user.setId(rs.getLong("id"));
                    user.setName(rs.getString("name"));
                    user.setAddress(rs.getString("address"));
                    user.setAmount(new BigDecimal(rs.getString("amount")));
                    return Optional.of(user);
                }
            } catch (SQLException ex) {
                throw new DataAccessException(ex);
            }
        } catch (SQLException ex) {
            throw new DataAccessException(ex);
        }
        return Optional.empty();
    }

    @Override
    public void update(Account a) {
        final String SQL = "update Account set name = ?, address = ?, amount = ? where id = ?";
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement statement = conn.prepareStatement(SQL)) {
            statement.setString(1, a.getName());
            statement.setString(2, a.getAddress());
            statement.setString(3, a.getAmount().toString());
            statement.setLong(4,a.getId());
            statement.executeUpdate();
        } catch (SQLException ex) {
            throw new DataAccessException(ex);
        }
    }

    @Override
    public Long findAccount(String name, String addr) {
        final String SQL = "select * from Account where name = ? and address = ?";
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement statement = conn.prepareStatement(SQL)) {
            statement.setString(1, name);
            statement.setString(2, addr);
            try (ResultSet rs = statement.executeQuery()) {
                if (rs.next()) {
                    Account user = new Account();
                    return rs.getLong("id");

                }
            } catch (SQLException ex) {
                throw new DataAccessException(ex);
            }
        } catch (SQLException ex) {
            throw new DataAccessException(ex);
        }
        throw new DataAccessException();
    }

    @Override
    public List<Account> getAllAccounts() {
        final String SQL = "select * from Account";
        List<Account> result = new ArrayList<>();
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement statement = conn.prepareStatement(SQL);
             ResultSet rs = statement.executeQuery();) {
            while (rs.next()) {
                result.add(new Account(
                        rs.getLong("id"),
                        rs.getString("name"),
                        rs.getString("address"),
                        new BigDecimal(rs.getString("amount")))
                );
            }
        } catch (SQLException ex) {
            throw new DataAccessException(ex);
        }

        return result;
    }

    @Override
    public void removeall() {
        final String SQL = "delete from Account";
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement statement = conn.prepareStatement(SQL)) {

            statement.executeUpdate();
        } catch (SQLException ex) {
            throw new DataAccessException(ex);
        }
    }


}
