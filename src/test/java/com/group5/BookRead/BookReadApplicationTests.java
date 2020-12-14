package com.group5.BookRead;


import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;


import org.springframework.security.core.userdetails.UsernameNotFoundException;
import com.group5.BookRead.models.Book;
import com.group5.BookRead.models.Friendship;
import com.group5.BookRead.models.MyBook;
import com.group5.BookRead.models.comment.TimelineComment;
import com.group5.BookRead.models.User;
import com.group5.BookRead.repositories.BookRepository;
import com.group5.BookRead.repositories.FriendshipRepository;
import com.group5.BookRead.repositories.MyBookRepository;
import com.group5.BookRead.repositories.TimelineCommentRepository;
import com.group5.BookRead.repositories.UserRepository;
import com.group5.BookRead.services.book.BasicBookService;
import com.group5.BookRead.services.friend.FriendshipService;
import com.group5.BookRead.services.timelineComment.RegularTimelineCommentService;
import com.group5.BookRead.services.user.RegularUserService;

import static org.mockito.Mockito.*;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.ArrayList;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

//@SpringBootTest(classes = BookReadApplication.class)
class BookReadApplicationTests {
//
//    @LocalServerPort
//    private int port;
//    
//    @Autowired
//    private TestRestTemplate restTemplate;
//    
//	@Test
//	public void testLogin() throws JSONException {
//	    AuthenticationRequest request = new AuthenticationRequest();
//	    request.setUsername("4444");
//	    request.setPassword("4444");
//	    ResponseEntity<AuthenticationResponse> responseEntity = this.restTemplate
//	            .postForEntity("http://localhost:" + port + "/login", request, AuthenticationResponse.class);
//	    System.out.println(responseEntity.getBody().getJwt());
	    
//	    String authToken = "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJzaGVycnlYaWFveWluZ0xpIiwiZXhwIjoxNjA3NTk4MDc1LCJpYXQiOjE2MDc1NjIwNzV9.jF7GIe4XAd3yCX7GPXI-8E3btqdwzjTSWJKcFRDXtuk";
//	    HttpHeaders headers = new HttpHeaders();
//	    headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
//	    headers.set("jwt", authToken);
//	    Map<String, String> params = new HashMap<String, String>();
//	    params.put("bookId", "27");
//	    params.put("bookshelf", "want to read");
//	    
// 	    ResponseEntity<String> response = this.restTemplate
// 	            .exchange("http://localhost:" + port + "/book/progress",
// 	                    HttpMethod.GET, 
// 	                    new HttpEntity<?>(headers), String.class, params);
//                .getForObject("http://localhost:" + port + "/book/progress?bookId=27&bookshelf=want to read", String.class);
//	    System.out.println(response);
//	    JSONObject obj = new JSONObject();
//	    double progress = (double) obj.get("progress");
//	    assertTrue( == 0);
	    
    private static UserRepository mockUserRepository;
    private static RegularUserService regularUserService;
    private static TimelineCommentRepository mockTimelineCommentRepository;
    private static RegularTimelineCommentService regularTimelineCommentService;
    private static FriendshipRepository mockFriendshipRepository;
    private static FriendshipService friendshipService;
    private static BookRepository mockBookRepository;
    private static MyBookRepository mockMyBookRepository;
    private static BasicBookService basicBookService;
    
    
    @BeforeAll
    public static void setUp() throws Exception {
        mockUserRepository = mock(UserRepository.class);
        regularUserService = new RegularUserService(mockUserRepository);
        mockTimelineCommentRepository = mock(TimelineCommentRepository.class);
        regularTimelineCommentService = new RegularTimelineCommentService(mockTimelineCommentRepository);
        mockFriendshipRepository = mock(FriendshipRepository.class);
        friendshipService = new FriendshipService(mockFriendshipRepository, mockUserRepository);
        mockBookRepository = mock(BookRepository.class);
        mockMyBookRepository = mock(MyBookRepository.class);
        basicBookService = new BasicBookService(mockBookRepository, mockMyBookRepository);
    }  
    
    @Test
    public void shouldThrowExceptionWhenUserNotFound() {
        int userId = 0;
        doReturn(null).when(mockUserRepository).findById(0);
        try {
            regularUserService.findByUserId(userId);
            fail();
        } catch(Exception ex) {
            assertTrue(ex.getClass() == UsernameNotFoundException.class);
        } 
    }
    
    @Test
    public void shouldThrowExceptionWhenUsernameIsNull() {
        doReturn(null).when(mockUserRepository).findByUsername(anyString());
        try {
            regularUserService.findByUsername("");
            fail();
        } catch(Exception ex) {
            assertTrue(ex.getClass() == UsernameNotFoundException.class);
        } 
    }
    
    @Test
    public void canSaveTimelineCommentObject() throws Exception {
        doReturn(new ArrayList<>()).when(mockTimelineCommentRepository).getTimelineCommentsByTimelineIdAndUserId(anyInt(), anyInt(), anyString());
        doReturn(1).when(mockTimelineCommentRepository).insert(anyObject());
        doReturn(null).when(mockTimelineCommentRepository).findById(1);
        TimelineComment timelineComment = new TimelineComment(0, null, null, 0, 0, null);
        TimelineComment res = regularTimelineCommentService.save(timelineComment);
        verify(mockTimelineCommentRepository).getTimelineCommentsByTimelineIdAndUserId(anyInt(), anyInt(), anyString());
        verify(mockTimelineCommentRepository).insert(anyObject());
        verify(mockTimelineCommentRepository).findById(anyInt());
        assertNull(res);
    }
    
    @Test
    public void canThrowExceptionWhenRemoveButNeverLiked() {
        doReturn(new ArrayList<>()).when(mockTimelineCommentRepository).getTimelineCommentsByTimelineIdAndUserId(anyInt(), anyInt(), anyString());
        try {
            TimelineComment timelineComment = new TimelineComment(0, null, null, 0, 0, null);
            int res = regularTimelineCommentService.removeById(anyInt(), anyInt(), anyString());
            fail();
        } catch(Exception ex) {
            assertEquals(ex.getMessage(), "You never liked");
        }
    }
    
    @Test
    public void canGetOneFriend() {
        Friendship friendship = new Friendship();
        int userId = 1;
        int friendId = 2;
        friendship.setId(0);
        friendship.setUserId(userId);
        friendship.setFriendId(friendId);
        ArrayList<Friendship> placeholder = new ArrayList<>();
        placeholder.add(friendship);
        User friend = new User();
        friend.setId(2);
        friend.setDefaultBookshelf("want to read");
        friend.setPassword("pass");
        friend.setUsername("friend");
        doReturn(placeholder).when(mockFriendshipRepository).findAllByUserId(userId);
        doReturn(friend).when(mockUserRepository).findById(friendId);
        ArrayList<String> res = (ArrayList<String>) friendshipService.getFriends(userId);
        assertTrue(res.size() == 1);
    }
    @Test
    public void canGetMultipleFriends() {
        Friendship friendship = new Friendship();
        int userId = 1;
        int friendId = 2;
        friendship.setId(0);
        friendship.setUserId(userId);
        friendship.setFriendId(friendId);
        
        Friendship friendship2 = new Friendship();
        int friendId2 = 3;
        friendship2.setId(1);
        friendship2.setUserId(userId);
        friendship2.setFriendId(friendId2);
        
        ArrayList<Friendship> placeholder = new ArrayList<>();
        placeholder.add(friendship);
        placeholder.add(friendship2);
        User friend = new User();
        friend.setId(2);
        friend.setDefaultBookshelf("want to read");
        friend.setPassword("pass");
        friend.setUsername("friend");
        User friend2 = new User();
        friend.setId(3);
        friend.setDefaultBookshelf("want to read");
        friend.setPassword("pass");
        friend.setUsername("friend2");
        doReturn(placeholder).when(mockFriendshipRepository).findAllByUserId(userId);
        doReturn(friend).when(mockUserRepository).findById(friendId);
        doReturn(friend2).when(mockUserRepository).findById(friendId2);
        ArrayList<String> res = (ArrayList<String>) friendshipService.getFriends(userId);
        assertTrue(res.size() == 2);
    }

    @Test
    public void canSaveBook() throws SQLIntegrityConstraintViolationException {
        doReturn(0).when(mockBookRepository).insert(anyObject());
        doReturn(null).when(mockBookRepository).findByIdentifier(anyObject());
        Book book = new Book();
        basicBookService.save(book);
        verify(mockBookRepository, times(1)).insert(anyObject());
        verify(mockBookRepository, times(1)).findByIdentifier(anyObject());
    }
    
    @Test
    public void canGetBook() throws SQLIntegrityConstraintViolationException {
        doReturn(null).when(mockBookRepository).findById(anyInt());
        basicBookService.getBook(5);
        verify(mockBookRepository, times(1)).findById(anyInt());
    }
    
    @Test
    public void canGetBookByNameAuthor() throws SQLIntegrityConstraintViolationException {
        doReturn(null).when(mockBookRepository).findByNameAndAuthor(anyString(), anyString());
        basicBookService.getBookByNameAuthor("name", "author");
        verify(mockBookRepository, times(1)).findByNameAndAuthor(anyString(), anyString());
    }
  
    
    @Test
    public void canGetMultipleMyBooks() throws SQLIntegrityConstraintViolationException {
        ArrayList<MyBook> res = new ArrayList<MyBook>();
        res.add(new MyBook());
        res.add(new MyBook());
        doReturn(res).when(mockMyBookRepository).findAllMybooks(anyInt(), anyInt());
        basicBookService.getMyBooks(5, 1);
        verify(mockMyBookRepository, times(1)).findAllMybooks(anyInt(), anyInt());
    }
	

}
