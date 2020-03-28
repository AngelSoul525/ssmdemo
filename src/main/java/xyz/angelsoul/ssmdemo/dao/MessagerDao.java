package xyz.angelsoul.ssmdemo.dao;

import org.springframework.stereotype.Repository;
import xyz.angelsoul.ssmdemo.model.Messager;

@Repository
public interface MessagerDao {
    /**
     * save messager information
     */
    public int saveMessagerInfo(Messager messager);

    /**
     * update messager information
     */
    public int updateMessagerInfo(Messager messager);

    /**
     * select messager information by username
     */
    public Messager selectMessagerByUsername(String username);

    /**
     * select messager information by nickname
     */
    public Messager selectMessagerByNickname(String nickname);
}
