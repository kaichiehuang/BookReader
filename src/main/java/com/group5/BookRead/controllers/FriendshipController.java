package com.group5.BookRead.controllers;

import com.group5.BookRead.services.friend.FriendshipService;
import com.group5.BookRead.services.friend.UserDidNotRequestToBeFriendException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestParam;




import javax.servlet.http.HttpServletResponse;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.List;
import java.util.Map;

@RestController
public class FriendshipController {

    @Autowired
    FriendshipService friendshipService;

    /**
     * <p> get a list of friend by user id
     * </p>
     * @param response response object
     * @return response message
     * @since 1.0
     */
    @GetMapping("/user/friend")
    public String getFriends(final HttpServletResponse response) {
        try {
            SecurityContext context = SecurityContextHolder.getContext();

            int userId = Integer.parseInt(context.getAuthentication()
                    .getPrincipal().toString());

            List<String> friendList = friendshipService.getFriends(userId);

            response.setStatus(HttpServletResponse.SC_OK);

            return "{\"friends\":\"" + friendList.toString() + "\"}";
        } catch (Exception e) {
            throw e;
        }
    }

    /**
     * <p> request a friendship to another user by username
     * </p>
     * @param json param object
     * @param response response object
     * @return response message
     * @since 1.0
     */
    @PostMapping("/user/friend")
    public String requestFriendship(@RequestBody final Map<String, String> json,
                                    final HttpServletResponse response) {
        try {
            SecurityContext context = SecurityContextHolder.getContext();

            // get parameters
            int userId = Integer.parseInt(context.getAuthentication()
                    .getPrincipal().toString());
            String requestFriendName = json.get("requestedFriendName");

            friendshipService.requestFriendship(userId, requestFriendName);

            response.setStatus(HttpServletResponse.SC_OK);

            return "{\"msg\":\"success\"}";
        } catch (Exception e) {
            throw e;
        }
    }

    /**
     * <p> accept a friendship
     * </p>
     * @param json param object
     * @param response response object
     * @return response message
     * @since 1.0
     */
    @PutMapping("/user/friend")
    public String acceptFriendship(
        @RequestParam(name = "acceptedFriendName",
            required = true) final String acceptedFriendName, 
            final HttpServletResponse response)
            throws SQLIntegrityConstraintViolationException,
                UserDidNotRequestToBeFriendException {
        try {
            SecurityContext context = SecurityContextHolder.getContext();

            // get parameters
            int userId = Integer.parseInt(context.getAuthentication()
                    .getPrincipal().toString());
            friendshipService.acceptFriendship(userId, acceptedFriendName);

            response.setStatus(HttpServletResponse.SC_OK);

            return "{\"msg\":\"success\"}";
        } catch (Exception e) {
            throw e;
        }
    }

    /**
     * <p> reject a friendship
     * </p>
     * @param json param object
     * @param response response object
     * @return response message
     * @since 1.0
     */
    @DeleteMapping("/user/friend")
    public String rejectFriendship(@RequestParam(name = "rejectFriendName",
        required = true) final String rejectFriendName,
        final HttpServletResponse response)
            throws SQLIntegrityConstraintViolationException,
                UserDidNotRequestToBeFriendException {
        try {
            SecurityContext context = SecurityContextHolder.getContext();

            // get parameters
            int userId = Integer.parseInt(context.getAuthentication()
                    .getPrincipal().toString());

            friendshipService.rejectFriendship(userId, rejectFriendName);

            response.setStatus(HttpServletResponse.SC_OK);

            return "{\"msg\":\"success\"}";
        } catch (Exception e) {
            throw e;
        }
    }
}
