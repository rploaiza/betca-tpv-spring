package es.upm.miw.restControllers;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.HttpClientErrorException;

import es.upm.miw.restControllers.AdminResource;
import es.upm.miw.restControllers.UserResource;

import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.http.HttpStatus;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@TestPropertySource(locations = "classpath:test.properties")
public class AdminResourceFunctionalTesting {

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Autowired
    private RestService restService;

    @Test
    public void testDeleteAndSeedDb() {
        restService.loginAdmin().restBuilder().path(AdminResource.ADMINS).path(AdminResource.DB).delete().build();
        try {
            restService.loginAdmin().restBuilder().path(UserResource.USERS).path(UserResource.MOBILE_ID).expand(666666002).get().build();
            fail();
        } catch (HttpClientErrorException httpError) {
            assertEquals(HttpStatus.NOT_FOUND, httpError.getStatusCode());
        }
        restService.loginAdmin().restBuilder().path(AdminResource.ADMINS).path(AdminResource.DB).post().build();
    }

    @Test
    public void testDeleteBdManagerUnauthorized() {
        thrown.expect(new HttpMatcher(HttpStatus.FORBIDDEN));
        restService.loginManager().restBuilder().path(AdminResource.ADMINS).path(AdminResource.DB).delete().build();
    }

    @Test
    public void testDeleteBdOperatorUnauthorized() {
        thrown.expect(new HttpMatcher(HttpStatus.FORBIDDEN));
        restService.loginOperator().restBuilder().path(AdminResource.ADMINS).path(AdminResource.DB).delete().build();
    }

    @Test
    public void testSeedBdManagerUnauthorized() {
        thrown.expect(new HttpMatcher(HttpStatus.FORBIDDEN));
        restService.loginManager().restBuilder().path(AdminResource.ADMINS).path(AdminResource.DB).post().build();
    }

    @Test
    public void testSeedBdOperatorUnauthorized() {
        thrown.expect(new HttpMatcher(HttpStatus.FORBIDDEN));
        restService.loginOperator().restBuilder().path(AdminResource.ADMINS).path(AdminResource.DB).post().build();
    }

}
