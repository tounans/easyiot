package com.tounans.easyiot.easyiotauth;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.jwt.Jwt;
import org.springframework.security.jwt.JwtHelper;
import org.springframework.security.jwt.crypto.sign.RsaVerifier;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest
class EasyiotAuthApplicationTests {

    @Test
    void contextLoads() {
    }


    //校验jwt令牌
    @Test
    public void testVerify(){
        //公钥
        String publickey = "-----BEGIN PUBLIC KEY-----MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAzUVnLWhlEwqeU2vkflhwkzh/ml39WWwqc7nCp9BTiJge8uIKo3VSCa5KhsXNbhDOFs+HR4BhmsfoMsbYnKsP9UF4diDuVIOBo5/Kn48DmlBeqUbiEDbWrsBmBQ40EZAZTW2+lFzDrBsVeUtokJYnwzTA/j3hB/WMgzVVeGSN9SylcE+MIT+1edHnyvrzfu/focM50F1yyFm67a0TfA6Cw+wPXsrDRicokFqAKYcnK3KpGZTeySnqtCiI7zBDvgs3F/hcU9/GpYgMYvJYdBR63Urj2956oc9KZuwSO0YWiawW8FbxWLDrwME2SuvusfaPEvM/nLp31mWlxWulBDHOJwIDAQAB-----END PUBLIC KEY-----";
        //jwt令牌
        String jwtString = "eyJhbGciOiJSUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1c2VyX25hbWUiOiJhZG1pbiIsInNjb3BlIjpbImFwcCJdLCJpZCI6MSwiZXhwIjoxNTg0MTUwODkwLCJhdXRob3JpdGllcyI6WyJTeXN0ZW1Vc2VySW5zZXJ0IiwiU3lzdGVtVXNlckRlbGV0ZSIsIlN5c3RlbVVzZXJVcGRhdGUiLCJTeXN0ZW1Db250ZW50VmlldyIsIlN5c3RlbVVzZXJWaWV3IiwiU3lzdGVtIl0sImp0aSI6IjQ5ODkxNzFlLWNmYTctNGMzZC04MjhmLTY1NzFiMDBjNTBhYyIsImNsaWVudF9pZCI6IlhjV2ViQXBwIn0.MbJWB6CLtO3L7NAi9YoG0b841sTU35ndR2WihWagnpOhjO3Pd66P85Yx4qPn7FU81pl0hqnfkeyBDALS9ByiZAA8dHyUOjuzdX_8fm4hsLjHz4ntK-sBtuPRDyqkq_km4J__CorV5JBPFvbLB7iMEADIAQFGXlVqBAIDDXYk6F5WI-9H6LQpBwesarOUZAlVjdMhMPPi8-uYaAroCQr9wV0Eumay6skqNQOzgnrOjrgFH0dAjZWlQI6teOsp9dk9OcpWzubSdS6rEPD2GLSA9HoHg-5rAFpdsgZOcjWViNpd4_kgfkWdR4XCMMV2MSxE0bH0j4Q4_7gl0U1H0UiOVA";
        //校验jwt令牌
        Jwt jwt = JwtHelper.decodeAndVerify(jwtString, new RsaVerifier(publickey));
        //拿到jwt令牌中自定义的内容
        /**
         * {"user_name":"admin","scope":["app"],"id":1,"exp":1584150890,"authorities":["SystemUserInsert","SystemUserDelete","SystemUserUpdate","SystemContentView","SystemUserView","System"],"jti":"4989171e-cfa7-4c3d-828f-6571b00c50ac","client_id":"XcWebApp"}
         */
        String claims = jwt.getClaims();
        System.out.println(claims);
    }

    @Test
    public void testPass(){
        BCryptPasswordEncoder encoder =new BCryptPasswordEncoder();

        String app = new BCryptPasswordEncoder().encode("secret");
        System.out.println(app);
    }
}
