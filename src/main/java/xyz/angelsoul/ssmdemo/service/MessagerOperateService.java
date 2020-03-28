package xyz.angelsoul.ssmdemo.service;

import xyz.angelsoul.ssmdemo.model.Messager;

import javax.servlet.http.HttpServletResponse;

public interface MessagerOperateService {

    /**
     * query username
     * @param username query if username exist
     * @return true return username else return null
     */
    public String queryUsername(String username);

    /**
     * get messager information by username
     * @param username query messager info by username
     * @return return messager info
     */
    public Messager getMessagerInfoByUsername(String username);

    /**
     * update messager Informaion
     * @param messager new messager info
     * @return new messager info
     */
    public Messager requestModifyMessagerInfo(Messager messager);

    /**
     * update messager password
     * @param newPassword new password
     * @return modify result 0: success; -1: fail
     */
    public int requestModifyPassword(String username, String originalPassword, String newPassword);

    /**
     * query nickname
     * @param nickname query if nickname exist
     * @return true return nickname else return null
     */
    public String queryNickname(String nickname);

    /**
     * save messager information to database
     * @param messager the information need save
     * @return
     */
    public Messager saveMessager(Messager messager);

    /**
     * messager login request
     * @param messager messager want to login
     * @param response http servlet response
     * @return password right return messager info else return ERROR_PASSWORD
     */
    public Object messagerLogin(Messager messager, HttpServletResponse response);
}
