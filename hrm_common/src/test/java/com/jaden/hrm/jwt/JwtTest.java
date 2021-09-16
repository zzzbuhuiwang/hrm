package com.jaden.hrm.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.junit.Test;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Jwt测试类
 */
public class JwtTest {

    /**
     * 通过jjwt创建token
     */
    @Test
    public void createJwt(){
        // 构建过期时间为3分钟
        long now = System.currentTimeMillis();
        long exp = now + 3000*60;
        JwtBuilder jwtBuilder = Jwts.builder().setId("28").setSubject("王")
                .setIssuedAt(new Date())
                //key不能太短 最短四个字符
                .signWith(SignatureAlgorithm.HS256, "jaden.hrm")
                .setExpiration(new Date(exp))
                //自定义claims存储数据
                .claim("role","admin")
                .claim("companyId","1")
                .claim("companyName","测试公司");
        String token = jwtBuilder.compact();
        System.out.println(token);
    }

    /**
     * 解析jwtToken字符串
     */
    @Test
    public void parseJwt(){
        String token = "eyJhbGciOiJIUzI1NiJ9.eyJqdGkiOiIyOCIsInN1YiI6IueOiyIsImlhdCI6MTYzMTgwMjU5NywiZXhwIjoxNjMxODAyNzc2LCJyb2xlIjoiYWRtaW4iLCJjb21wYW55SWQiOiIxIiwiY29tcGFueU5hbWUiOiLmtYvor5Xlhazlj7gifQ.XSJAj9lvQvaciGlO3dcbj2Vn7PUHcj392Eu3xBI2Qc0";
        Claims claims = Jwts.parser().setSigningKey("jaden.hrm").parseClaimsJws(token).getBody();

        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");

        //私有数据存放在claims
        System.out.println("id:"+claims.getId());
        System.out.println("subject:"+claims.getSubject());
        System.out.println("签发时间:"+sdf.format(claims.getIssuedAt()));
        System.out.println("过期时间:"+sdf.format(claims.getExpiration()));
        System.out.println("当前时间:"+sdf.format(new Date()) );

        //解析自定义claim中的内容
        String companyId = (String)claims.get("companyId");
        String companyName = (String)claims.get("companyName");

        System.out.println(companyId + "---" + companyName);
    }
}
