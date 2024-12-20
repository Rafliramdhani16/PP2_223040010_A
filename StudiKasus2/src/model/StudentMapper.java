package model;

import org.apache.ibatis.annotations.*;
import java.util.List;

public interface StudentMapper {
    @Select("SELECT * FROM mahasiswa")
    List<Student> getAllStudents();

    @Insert("INSERT INTO mahasiswa (nim, nama, jurusan, angkatan, email) " +
            "VALUES (#{nim}, #{nama}, #{jurusan}, #{angkatan}, #{email})")
    void insertStudent(Student student);

    @Update("UPDATE mahasiswa SET nama=#{nama}, jurusan=#{jurusan}, " +
            "angkatan=#{angkatan}, email=#{email} WHERE nim=#{nim}")
    void updateStudent(Student student);

    @Delete("DELETE FROM mahasiswa WHERE nim=#{nim}")
    void deleteStudent(String nim);
}
