package MembershipORM.src.mapper;

import java.util.List;
import MembershipORM.src.model.Member;
import MembershipORM.src.model.JenisMember;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.One;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

public interface MemberMapper {
    @Insert("INSERT INTO member(id, nama, jenis_member_id) VALUES(#{id}, #{nama}, #{jenisMemberId})")
    public int insert(Member member);

    @Update("UPDATE member SET nama = #{nama}, jenis_member_id = #{jenisMemberId} WHERE id = #{id}")
    public int update(Member member);

    @Delete("DELETE FROM member WHERE id = #{id}")
    public int delete(Member member);

    @Select("SELECT * FROM member")
    @Results(value = {
            @Result(property = "id", column = "id"),
            @Result(property = "nama", column = "nama"),
            @Result(property = "jenisMemberId", column = "jenis_member_id"),
            @Result(property = "jenisMember", column = "jenis_member_id",
                    one = @One(select = "MembershipORM.src.mapper.MemberMapper.getJenisMember"))
    })
    public List<Member> findAll();

    @Select("SELECT * FROM jenis_member WHERE id = #{id}")
    @Results(value = {
            @Result(property = "id", column = "id"),
            @Result(property = "nama", column = "nama")
    })
    public JenisMember getJenisMember(String id);
}