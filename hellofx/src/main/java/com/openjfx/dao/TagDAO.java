package com.openjfx.dao;

import com.openjfx.models.Tag;
import com.openjfx.models.Database;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TagDAO {
    public static void insertTag(Tag tag) {
        String sql = "INSERT INTO tags(name, color) VALUES(?,?)";

        try (Connection conn = Database.connect(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, tag.getName());
            stmt.setString(2, tag.getColor());
            stmt.executeUpdate();
            System.out.println("New Tag created");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void updateTag(Tag tag) {
        String sql = "UPDATE tags SET name = ?, color ? WHERE id = ?";

        try (Connection conn = Database.connect(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, tag.getName());
            stmt.setString(2, tag.getColor());
            stmt.executeUpdate();
            System.out.println("Tag updated");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void deleteTag(int id) {
        String sql = "DELETE FROM tags WHERE id = ?";

        try (Connection conn = Database.connect(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
            System.out.println("Tag deleted");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public static List<Tag> getAllTags() {
        String sql = "SELECT id, name, color FROM tags";
        List<Tag> tags = new ArrayList<>();

        try (Connection conn = Database.connect();
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Tag tag = new Tag();
                tag.setId(rs.getInt("id"));
                tag.setName(rs.getString("name"));
                tag.setColor(rs.getString("color"));
                tags.add(tag);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return tags;
    }

    public static Tag getTagById(int id) {
        String sql = "SELECT name, color FROM tags WHERE id = ?";
        Tag tag = null;

        try (Connection conn = Database.connect();
                PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                tag = new Tag();
                tag.setId(id);
                tag.setName(rs.getString("name"));
                tag.setColor(rs.getString("color"));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return tag;
    }
}
