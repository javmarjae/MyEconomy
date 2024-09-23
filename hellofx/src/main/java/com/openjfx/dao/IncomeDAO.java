package com.openjfx.dao;

import com.openjfx.models.Tag;
import com.openjfx.models.Income;
import com.openjfx.models.Database;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class IncomeDAO {
    public static void insertIncome(Income income) {
        String sql = "INSERT INTO incomes(name, money, date, tag) VALUES(?,?,?,?)";

        try (Connection conn = Database.connect(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, income.getName());
            stmt.setDouble(2, income.getMoney());
            stmt.setDate(3, new Date(income.getDate().getTime()));
            stmt.setInt(4, income.getTag().getId());
            stmt.executeUpdate();
            System.out.println("New Income created");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void updateIncome(Income income) {
        String sql = "UPDATE incomes SET name = ?, money = ?, date = ?, tag_id = ? WHERE id = ?";

        try (Connection conn = Database.connect();
                PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, income.getName());
            pstmt.setDouble(2, income.getMoney());
            pstmt.setDate(3, new Date(income.getDate().getTime()));
            pstmt.setInt(4, income.getTag().getId());
            pstmt.executeUpdate();
            System.out.println("Income updated");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void deleteIncome(int id) {
        String sql = "DELETE FROM incomes WHERE id = ?";

        try (Connection conn = Database.connect();
                PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            pstmt.executeUpdate();
            System.out.println("Income deleted");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public static List<Income> getAllIncomes() {
        String sql = "SELECT id, name, money, date, tag_id FROM incomes";
        List<Income> incomes = new ArrayList<>();

        try (Connection conn = Database.connect();
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Income income = new Income();
                income.setId(rs.getInt("id"));
                income.setName(rs.getString("name"));
                income.setMoney(rs.getDouble("money"));
                income.setDate(rs.getDate("date"));
                int tagId = rs.getInt("tag_id");
                income.setTag(TagDAO.getTagById(tagId));
                incomes.add(income);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return incomes;
    }

    public static Income getIncomeById(int id) {
        String sql = "SELECT id, name, money, date, tag_id FROM incomes WHERE id = ?";
        Income income = null;

        try (Connection conn = Database.connect();
                PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                income = new Income();
                income.setId(rs.getInt("id"));
                income.setName(rs.getString("name"));
                income.setMoney(rs.getDouble("money"));
                income.setDate(rs.getDate("date"));
                int tagId = rs.getInt("tag_id");
                income.setTag(TagDAO.getTagById(tagId));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return income;
    }
}
