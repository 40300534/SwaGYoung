package com.breeze.interceptor;

import com.alibaba.fastjson.JSONObject;
import com.breeze.annotation.Admin;
import com.breeze.annotation.LoginRequired;
import com.breeze.mapper.UserMapper;
import com.breeze.pojo.User;
import com.breeze.util.RedisLoginUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

/**
 * @author tx
 * @date 2019-6-26 17:54:05
 */
@Slf4j
public class AuthorizationInterceptor implements HandlerInterceptor {
    /**
     * 存放鉴权信息的Header名称，默认是token
     */
    private String httpHeaderToken = "token";
    /**
     * 存放在Header里的userId
     */
    private String httpHeaderUserId = "userId";
    /**
     * 鉴权失败后返回的HTTP错误码，默认为401
     */
    private int unauthorizedErrorCode = HttpServletResponse.SC_UNAUTHORIZED;

    @Autowired
    public RedisLoginUtils redisLoginUtils;

    @Autowired
    public HttpServletRequest request;

    @Autowired
    public HttpServletResponse response;

    @Autowired
    private UserMapper userMapper;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws IOException {
        if (!(handler instanceof HandlerMethod)) {
            return true;
        }
        response.setCharacterEncoding("UTF-8");
        HandlerMethod handlerMethod = (HandlerMethod) handler;
        Method method = handlerMethod.getMethod();

        // 如果打上了LoginRequired注解则需要验证token
        if (method.getAnnotation(LoginRequired.class) != null || handlerMethod.getBeanType().getAnnotation(LoginRequired.class) != null) {
            String token = request.getHeader(httpHeaderToken);
            String HeaderUserId = request.getHeader(httpHeaderUserId);
            String userId="";
            if (token != null && token.length() != 0 && !token.trim().equals("")) {
                try {
                    userId = redisLoginUtils.get(token).toString();
                    if (!HeaderUserId.equals(userId)) {
                        response.getWriter().println("用户不统一");
                        return false;
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    log.info("get token null");

                    response.getWriter().println("请您重新登录！有误的Token或它已经失效了");
                    return false;
                }
                if (userId != null && userId.length() != 0) {
                    if (redisLoginUtils.get(userId).equals(token)) {
                        Long tokeBirthTime = Long.valueOf((String) redisLoginUtils.get(userId + token));
                        Long diff = System.currentTimeMillis() - tokeBirthTime;
                        //重新设置Redis中的token过期时间
                        if (diff > 10) {
                            Long newBirthTime = System.currentTimeMillis();
                            redisLoginUtils.set(token, userId);
                            redisLoginUtils.expire(token, 1000 * 60*5);
                            redisLoginUtils.set(userId + token, newBirthTime.toString());
                            redisLoginUtils.expire("" + userId + token, 1000 * 60*5);
                            redisLoginUtils.set("" + userId, token);
                            redisLoginUtils.expire("" + userId, 1000 * 60*5);
                        }
                    }
                }
            } else {
                JSONObject jsonObject = new JSONObject();
                PrintWriter out = null;
                try {
                    response.setStatus(unauthorizedErrorCode);
                    response.setContentType(MediaType.APPLICATION_JSON_VALUE);
                    jsonObject.put("code", response.getStatus());
                    jsonObject.put("message", HttpStatus.UNAUTHORIZED);
                    out = response.getWriter();
                    out.println(jsonObject);
                    return false;
                } catch (Exception e) {
                    e.printStackTrace();
                    log.info("响应失败");
                } finally {
                    if (null != out) {
                        out.flush();
                        out.close();
                    }
                }

            }

        }
        if (method.getAnnotation(Admin.class) != null || handlerMethod.getBeanType().getAnnotation(Admin.class) != null) {
            if (hasAnnotationOnMethod(method, Admin.class)) {
                //只有管理员能操作
                try {
                    User user = getUser();
                    if (user.getUserTypeId() != UserRoleConstant.ADMIN) {
                        response.setCharacterEncoding("utf-8");
                        response.getWriter().println("只有管理员能操作");
                        return false;
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                    log.info("Admin抛出异常");
                    return false;
                }
            }
        }
        return true;
    }

    private User getUser() {
        String token = request.getHeader(httpHeaderToken);
        Long UserId =  Long.valueOf(redisLoginUtils.get(token).toString());
        User user = userMapper.findById(UserId);
        return user;
    }

    private boolean hasAnnotationOnMethod(Method method, Class annotationClazz) {
        //使用反射获取注解信息
        Annotation a = method.getAnnotation(annotationClazz);
        if (a == null) {
            return false;
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
    }
}
