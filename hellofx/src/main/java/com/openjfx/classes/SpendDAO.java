package com.openjfx.classes;

import com.openjfx.models.Tag;
import com.openjfx.models.Spend;
import com.openjfx.models.Database;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SpendDAO {
    public static void insertSpend(Spend spend) {
        String sql = "INSERT INTO spends(name, money, quantity, date, tag) VALUES(?,?,?,?,?)";

        try (Connection conn = Database.connect(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, spend.getName());
            stmt.setDouble(2, spend.getMoney());
            stmt.setInt(3, spend.getQuantity());
            stmt.setDate(4, new Date(spend.getDate().getTime()));
            stmt.setInt(5, spend.getTag().getId());
            stmt.executeUpdate();
            System.out.println("New Spend created");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void updateSpend(Spend spend) {
        String sql = "UPDATE spends SET name = ?, money = ?, quantity = ?, date = ?, tag_id = ? WHERE id = ?";

        try (Connection conn = Database.connect();
                PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, spend.getName());
            pstmt.setDouble(2, spend.getMoney());
            pstmt.setInt(3, spend.getQuantity());
            pstmt.setDate(4, new Date(spend.getDate().getTime()));
            pstmt.setInt(5, spend.getTag().getId());
            pstmt.executeUpdate();
            System.out.println("Spend updated");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void deleteSpend(int id) {
        String sql = "DELETE FROM spends WHERE id = ?";

        try (Connection conn = Database.connect();
                PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            pstmt.executeUpdate();
            System.out.println("Spend deleted");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public static List<Spend> getAllSpends() {
        String sql = "SELECT id, name, money, quantity, date, tag_id FROM spends";
        List<Spend> spends = new ArrayList<>();

        try (Connection conn = Database.connect();
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Spend spend = new Spend();
                spend.setId(rs.getInt("id"));
                spend.setName(rs.getString("name"));
                spend.setMoney(rs.getDouble("money"));
                spend.setQuantity(rs.getInt("quantity"));
                spend.setDate(rs.getDate("date"));
                int tagId = rs.getInt("tag_id");
                spend.setTag(TagDAO.getTagById(tagId));
                spends.add(spend);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return spends;
    }

    public static Spend getSpendById(int id) {
        String sql = "SELECT id, name, money, quantity, date, tag_id FROM spends WHERE id = ?";
        Spend spend = null;

        try (Connection conn = Database.connect();
                PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                spend = new Spend();
                spend.setId(rs.getInt("id"));
                spend.setName(rs.getString("name"));
                spend.setMoney(rs.getDouble("money"));
                spend.setQuantity(rs.getInt("quantity"));
                spend.setDate(rs.getDate("date"));
                int tagId = rs.getInt("tag_id");
                spend.setTag(TagDAO.getTagById(tagId));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return spend;
    }

}