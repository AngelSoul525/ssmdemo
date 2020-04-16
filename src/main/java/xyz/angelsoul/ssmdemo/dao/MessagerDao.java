package xyz.angelsoul.ssmdemo.dao;

import org.springframework.stereotype.Repository;
import xyz.angelsoul.ssmdemo.model.Messager;

@Repository
public interface MessagerDao {
    /**
     * save messager information
     */
    int saveMessagerInfo(Messager messager);

    /**
     * update messager information
     */
    int updateMessagerInfo(Messager messager);

    /**
     * select messager information by username
     */
    Messager selectMessagerByUsername(String username);

    /**
     * select messager information by nickname
     */
    Messager selectMessagerByNickname(String nickname);

    /**
     * select a messager for keeping database connection running
     */
    Messager selectMessager();
}
