package Membership.src.dao;

import Membership.src.db.MySqlConnection;
import Membership.src.model.JenisMember;
import Membership.src.model.Member;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MemberDao {

    public int insert(Member member) {
        int result = -1;
        try (Connection connection = MySqlConnection.getInstance().getConnection()) {
            PreparedStatement statement = connection.prepareStatement("INSERT INTO member (id, nama, jenis_member_id) VALUES (?, ?, ?)");
            statement.setString(1, member.getId());
            statement.setString(2, member.getNama());
            statement.setString(3, member.getJenisMember().getId());
            result = statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    public int update(Member member) {
        int result = -1;
        try (Connection connection = MySqlConnection.getInstance().getConnection()) {
            PreparedStatement statement = connection.prepareStatement("UPDATE member SET nama = ?, jenis_member_id = ? WHERE id = ?");
            statement.setString(1, member.getNama());
            statement.setString(2, member.getJenisMember().getId());
            statement.setString(3, member.getId());
            result = statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    public int delete(Member member) {
        int result = -1;
        try (Connection connection = MySqlConnection.getInstance().getConnection()) {
            PreparedStatement statement = connection.prepareStatement("DELETE FROM member WHERE id = ?");
            statement.setString(1, member.getId());
            result = statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    public List<Member> findAll() {
        List<Member> list = new ArrayList<>();
        try (Connection connection = MySqlConnection.getInstance().getConnection()) {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT member.id, member.nama, jenis_member.id AS jenis_member_id, jenis_member.nama AS jenis_member_nama FROM member JOIN jenis_member ON member.jenis_member_id = jenis_member.id");
            while (resultSet.next()) {
                Member member = new Member();
                JenisMember jenisMember = new JenisMember();
                member.setId(resultSet.getString("id"));
                member.setNama(resultSet.getString("nama"));
                jenisMember.setId(resultSet.getString("jenis_member_id"));
                jenisMember.setNama(resultSet.getString("jenis_member_nama"));
                member.setJenisMember(jenisMember);
                list.add(member);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }
}