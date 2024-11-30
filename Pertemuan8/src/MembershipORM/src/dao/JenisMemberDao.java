package MembershipORM.src.dao;

import java.util.List;
import MembershipORM.src.model.JenisMember;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

public class JenisMemberDao {
    private final SqlSessionFactory sqlSessionFactory;

    public JenisMemberDao(SqlSessionFactory sqlSessionFactory) {
        this.sqlSessionFactory = sqlSessionFactory;
    }

    public int insert(JenisMember jenisMember) {
        try (SqlSession session = sqlSessionFactory.openSession()) {
            int result = session.insert("MembershipORM.src.mapper.JenisMemberMapper.insert", jenisMember);
            session.commit();
            return result;
        }
    }

    public int update(JenisMember jenisMember) {
        try (SqlSession session = sqlSessionFactory.openSession()) {
            int result = session.update("MembershipORM.src.mapper.JenisMemberMapper.update", jenisMember);
            session.commit();
            return result;
        }
    }

    public int delete(JenisMember jenisMember) {
        try (SqlSession session = sqlSessionFactory.openSession()) {
            int result = session.delete("MembershipORM.src.mapper.JenisMemberMapper.delete", jenisMember);
            session.commit();
            return result;
        }
    }

    public List<JenisMember> findAll() {
        try (SqlSession session = sqlSessionFactory.openSession()) {
            return session.selectList("MembershipORM.src.mapper.JenisMemberMapper.findAll");
        }
    }
}