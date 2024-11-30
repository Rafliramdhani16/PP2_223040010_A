package MembershipORM.src.dao;

import java.util.List;
import MembershipORM.src.model.Member;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

public class MemberDao {
    private final SqlSessionFactory sqlSessionFactory;

    public MemberDao(SqlSessionFactory sqlSessionFactory) {
        this.sqlSessionFactory = sqlSessionFactory;
    }

    public int insert(Member member) {
        try (SqlSession session = sqlSessionFactory.openSession()) {
            int result = session.insert("MembershipORM.src.mapper.MemberMapper.insert", member);
            session.commit();
            return result;
        }
    }

    public int update(Member member) {
        try (SqlSession session = sqlSessionFactory.openSession()) {
            int result = session.update("MembershipORM.src.mapper.MemberMapper.update", member);
            session.commit();
            return result;
        }
    }

    public int delete(Member member) {
        try (SqlSession session = sqlSessionFactory.openSession()) {
            int result = session.delete("MembershipORM.src.mapper.MemberMapper.delete", member);
            session.commit();
            return result;
        }
    }

    public List<Member> findAll() {
        try (SqlSession session = sqlSessionFactory.openSession()) {
            return session.selectList("MembershipORM.src.mapper.MemberMapper.findAll");
        }
    }
}