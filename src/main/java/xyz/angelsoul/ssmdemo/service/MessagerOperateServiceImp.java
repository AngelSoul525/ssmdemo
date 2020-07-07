package xyz.angelsoul.ssmdemo.service;

import org.springframework.stereotype.Service;
import xyz.angelsoul.ssmdemo.dao.MessagerDao;
import xyz.angelsoul.ssmdemo.model.Messager;
import xyz.angelsoul.ssmdemo.utils.CONSTANTS;
import xyz.angelsoul.ssmdemo.utils.RETURN_CODE;
import xyz.angelsoul.ssmdemo.utils.RSAUtils;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.rmi.server.ExportException;
import java.util.Date;

@Service("messagerOperateService")
public class MessagerOperateServiceImp implements MessagerOperateService {
    @Resource
    private MessagerDao messagerDao;


    @Override
    public String queryUsername(String username) {
        if(username == null) {
            throw new RuntimeException("用户不存在");
        }
        Messager messager = messagerDao.selectMessagerByUsername(username);
        if(messager == null) {
            return null;
        } else {
            return messager.getUsername();
        }
    }

    @Override
    public String queryNickname(String nickname) {
        if(nickname == null) {
            throw new RuntimeException("用户不存在");
        }
        Messager messager = messagerDao.selectMessagerByNickname(nickname);
        if(messager == null) {
            return null;
        } else {
            return messager.getNickname();
        }
    }

    @Override
    public Messager getMessagerInfoByUsername(String username) {
        if(username == null) {
            throw new RuntimeException("用户不存在");
        }
        return messagerDao.selectMessagerByUsername(username);
    }

    @Override
    public Messager requestModifyMessagerInfo(Messager messager) {
        if(messager == null) {
            throw new RuntimeException("用户不存在");
        }
        int res = messagerDao.updateMessagerInfo(messager);
        if(res == CONSTANTS.SUCCESS_DB_OPERATE) {
            return messager;
        } else {
            return null;
        }
    }

    @Override
    public int requestModifyPassword(String username, String originalPassword, String newPassword) {
        if(username == null || originalPassword == null || newPassword == null) {
            throw new RuntimeException("用户不存在");
        }

        Messager checkRes = messagerDao.selectMessagerByUsername(username);
        if(checkRes == null) {
            throw new RuntimeException("用户不存在");
        }

        try {
            if(! RSAUtils.RSADecrypt(checkRes.getPassword()).equals(RSAUtils.RSADecrypt(originalPassword))) {
                throw new RuntimeException("密码错误");
            }
        } catch (Exception e) {
            System.out.println(e);
            throw new RuntimeException("服务器异常，请稍后重试");
        }

        Messager messager = new Messager();
        messager.setPassword(newPassword);
        messager.setUsername(username);

        return messagerDao.updateMessagerInfo(messager);
    }

    @Override
    public Messager saveMessager(Messager messager) {
        if(messager == null) {
            throw new RuntimeException("用户不存在");
        }
//        System.out.println(messager.toString());
        messager.setCreateTime(new Date().getTime());
        int res = messagerDao.saveMessagerInfo(messager);
        if(res > 0) {
            return messager;
        }
        System.out.println(res);
        return null;
    }

    @Override
    public Object messagerLogin(Messager messager, HttpServletResponse response) {
        if(messager == null || messager.getUsername() == null) {
            throw new RuntimeException("用户不存在");
        }

        /*
            attention: !!!!!!!! database exception not handle
         */
        Messager res = messagerDao.selectMessagerByUsername(messager.getUsername());
        if(res != null){
            System.out.println("[messagerLogin] " + res.toString());
            try {
                if(!(RSAUtils.RSADecrypt(res.getPassword()).equals(RSAUtils.RSADecrypt(messager.getPassword())))) {
                    return RETURN_CODE.ERROR_PASSWORD;
                }
            } catch (Exception e) {
                System.out.println(e);
                throw new RuntimeException("服务器异常，请稍后重试");
            }
            Messager resMessager = new Messager();
            resMessager.setUsername(res.getUsername());
            resMessager.setNickname(res.getNickname());
            return resMessager;
        }
        return null;
    }
}
