
<%@page import="models.Comment"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="models.Post"%>
<%@page import="java.util.ArrayList"%>
<%@page import="DAO.DAO"%>
<%@page import="models.User"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <link rel="stylesheet" href="css/bootstrap.min.css">
        <link rel="stylesheet" href="css/AlumniHomePage.css">
        <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Oswald">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.0/jquery.min.js"></script>
        <script src="js/bootstrap.min.js"></script>
        <title>Alumni Home Page</title>
    </head>
    <body>
        <h1 class="text-center"><b>Alumni</b>Home</h1>
        
        <!--NAVIGATION MENU -->
        <%User user = (User) request.getAttribute("user");%>
        <%DAO dao = new DAO();%>
        <%user.setFriendList(dao.getFriends(user.getUserID()));%>
        <%ArrayList<Post> post = dao.getAllPosts();%>
        <%ArrayList<User> friendRequest = dao.getReceivedRequests(user.getUserID());%>
        <%int postID = 1  ;%>
        <%SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");%>
        <nav class="navbar navbar-expand-lg navbar-light bg-light">
            <div class="container-fluid">
                <a class="navbar-brand"><b>VZAP</b>Alumni</a>
                <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
                    <span class="navbar-toggler-icon"></span>
                </button>
                <div class="collapse navbar-collapse" id="navbarSupportedContent">
                    <ul class="navbar-nav me-auto mb-2 mb-lg-0">
                        <li class="nav-item">
                            <a class="nav-link active" aria-current="page" href="HomePage.jsp">Home</a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" href="#">Inbox</a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" href="#">Code Cloud</a>
                        </li>
                        <li class="nav-item dropdown">
                            <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button" data-bs-toggle="dropdown" aria-expanded="false">
                                Groups
                            </a>
                            <ul class="dropdown-menu" aria-labelledby="navbarDropdown">
                                <li><a class="dropdown-item" href="#">Group1</a></li>
                                <li><a class="dropdown-item" href="#">Group2</a></li>
                                <li><hr class="dropdown-divider"></li>
                                <li><a class="dropdown-item" data-bs-toggle="modal" href="" data-bs-target="#CreateGroup">Create New Group</a></li>
                            </ul>
                        </li>
                    </ul>

                    <ul class="navbar-nav ms-auto">
                        <li class="nav-item">
                            <a class="nav-link" data-bs-toggle="modal" href="" data-bs-target="#MyAccount">My Account</a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" href="AlumniLogin.jsp">Logout</a>
                        </li>
                    </ul>
                </div>
            </div>
        </nav>

        <img class="img-height" src="images/roman-bozhko-PypjzKTUqLo-unsplash.jpg">
        
        <!--NOTICE BOARD CAROUSEL-->
        <h1 class="h1"><b>NOTICE</b>BOARD</h1>
        <div id="carouselExampleInterval" class="carousel slide carousel1-position" data-bs-ride="carousel">
            <div class="carousel-inner">
                <!-- A POST ITEM -->
                <div class="carousel-item active text-center" data-bs-interval="10000">
                    <img src="images/postIcon5.png" class=" icon-size">
                    <p class="post-heading"><%=post.get(0).getTitle()%><%=post.size()%></p>
                    <p class="post-author"><%=dao.getUser(post.get(0).getOwnerID()).getName()%> <%=dao.getUser(post.get(0).getOwnerID()).getSurname()%></p>
                    <div class="container text-center" >
                        <a class="post-description"  data-bs-toggle="modal" data-bs-target="#PostLearnMore0">Learn More</a>
                    </div>
                </div>
                <%for(int j=1;j<post.size();j++){%>
                <div class="carousel-item text-center" data-bs-interval="10000">
                    <img src="images/postIcon5.png" class=" icon-size">
                    <p class="post-heading"><%=post.get(j).getTitle()%><%=post.size()%></p>
                    <p class="post-author"><%=dao.getUser(post.get(j).getOwnerID()).getName()%> <%=dao.getUser(post.get(j).getOwnerID()).getSurname()%></p>
                    <div class="container text-center" >
                        <a class="post-description"  data-bs-toggle="modal" data-bs-target="#PostLearnMore<%=j%>">Learn More</a>
                    </div>
                </div>
                <%}%>
            </div>
            <button class="carousel-control-prev" type="button" data-bs-target="#carouselExampleInterval" data-bs-slide="prev">
                <span class="carousel-control-prev-icon" aria-hidden="true"></span>
                <span class="visually-hidden">Previous</span
            </button>
            <button class="carousel-control-next" type="button" data-bs-target="#carouselExampleInterval" data-bs-slide="next">
                <span class="carousel-control-next-icon" aria-hidden="true"></span>
                <span class="visually-hidden">Next</span>
            </button>
        </div>
        
        <!--CURRENT FRIENDS CAROUSEL -->
        <h1 class="h2"><b>YOUR</b>FRIENDS</h1>
        <div id="carousel2" class="carousel slide carousel2-position" data-bs-ride="carousel">
            <div class="carousel-inner">
                <!-- A FRIEND -->
                
                <div class="carousel-item active text-center" data-bs-interval="10000">
                    <img src="images/friendIcon3.png" class="friend-icon-size">
                    <p class="post-heading white-color"><b><%=user.getFriendList().get(0).getSurname()%></b>NAME<%=user.getFriendList().get(0).getName()%></p>
                    <div class="container text-center" >
                        <a class="post-description white-color" href="" data-bs-toggle="modal" data-bs-target="#FriendDetails0">Friend Details</a>
                    </div>
                </div>
                
                <!-- A FRIEND -->
                <%for(int i = 1; i<user.getFriendList().size();i++){%>
                    <div class="carousel-item text-center" data-bs-interval="10000">
                        <img src="images/friendIcon3.png" class="friend-icon-size">
                        <p class="post-heading white-color"><b><%=user.getFriendList().get(i).getSurname()%></b>NAME<%=user.getFriendList().get(i).getName()%></p>
                        <div class="container text-center" >
                            <a class="post-description white-color" href="" data-bs-toggle="modal" data-bs-target="#FriendDetails<%=i%>">Friend Details</a>
                        </div> 
                    </div>
                <%}%>
                <!-- A FRIEND -->
                <div class="carousel-item text-center" data-bs-interval="10000">
                    <img src="images/friendIcon3.png" class="friend-icon-size">
                    <p class="post-heading white-color"><b>PERSON</b>NAME</p>
                    <div class="container text-center" >
                        <a class="post-description white-color" href="" data-bs-toggle="modal" data-bs-target="#FriendDetails">Friend Details</a>
                    </div>
                </div>
            </div>
            <button class="carousel-control-prev post-black-color" type="button" data-bs-target="#carousel2" data-bs-slide="prev">
                <span class="carousel-control-prev-icon" aria-hidden="true"></span>
                <span class="visually-hidden">Previous</span>
            </button>
            <button class="carousel-control-next post-black-color" type="button" data-bs-target="#carousel2" data-bs-slide="next">
                <span class="carousel-control-next-icon" aria-hidden="true"></span>
                <span class="visually-hidden">Next</span>
            </button>
        </div>
        
        <!--FRIEND REQUESTS CAROUSEL -->
        <h1 class="h3"><b>FRIEND</b>REQUESTS</h1>
        <div id="carousel3" class="carousel slide carousel3-position" data-bs-ride="carousel">
            <div class="carousel-inner">
                <!-- A REQUEST -->
                <%for(int i=0;i<=friendRequest.size();i++){%>
                <div class="carousel-item active text-center" data-bs-interval="10000">
                    <img src="images/friendRequestIcon3.png" class="friend-icon-size">
                    <p class="post-heading white-color"><b>0PERSON</b>NAME</p>
                    <div class="container text-center" >
                        <button type="button" class="btn btn-outline-dark">Accept</button>
                        <button type="button" class="btn btn-outline-dark">Decline</button>                    
                    </div>
                </div>
                <%}%>
                <!-- A REQUEST -->
                <div class="carousel-item text-center" data-bs-interval="10000">
                    <img src="images/friendRequestIcon3.png" class="friend-icon-size">
                    <p class="post-heading white-color"><b>PERSON</b>NAME</p>
                    <div class="container text-center" >
                        <button type="button" class="btn btn-outline-dark">Accept</button>
                        <button type="button" class="btn btn-outline-dark">Decline</button>                    
                    </div>
                </div>
                <!-- A REQUEST -->
                <div class="carousel-item text-center" data-bs-interval="10000">
                    <img src="images/friendRequestIcon3.png" class="friend-icon-size">
                    <p class="post-heading white-color"><b>PERSON</b>NAME</p>
                    <div class="container text-center" >
                        <button type="button" class="btn btn-outline-dark">Accept</button>
                        <button type="button" class="btn btn-outline-dark">Decline</button>
                    </div>
                </div>
            </div>
            <button class="carousel-control-prev post-black-color" type="button" data-bs-target="#carousel3" data-bs-slide="prev">
                <span class="carousel-control-prev-icon" aria-hidden="true"></span>
                <span class="visually-hidden">Previous</span>
            </button>
            <button class="carousel-control-next post-black-color" type="button" data-bs-target="#carousel3" data-bs-slide="next">
                <span class="carousel-control-next-icon" aria-hidden="true"></span>
                <span class="visually-hidden">Next</span>
            </button>
        </div>
        
        <!--ADD NEW FRIEND BUTTON -->
        <button type="button" class="btn btn-outline-dark add-friend-button" data-bs-toggle="modal" data-bs-target="#addNewFriend">Add New Friend</button>
        
        <!--ADD NEW FRIEND POPUP -->
        <div class="modal fade" id="addNewFriend" data-bs-backdrop="static" data-bs-keyboard="false" tabindex="-1" aria-labelledby="staticBackdropLabel" aria-hidden="true">
            <div class="modal-dialog modal-dialog-centered">
                <div class="modal-content">
                    <div class="modal-header">
                        <h5 class="modal-title oswald" id="staticBackdropLabel">Add New Friend</h5>
                        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                    </div>
                    <div class="modal-body">
                        <form name="addFriendForm">
                        <input class="form-control" type="email" placeholder="Enter their Email" name="email" required>
                        </form>
                    </div>
                    <div class="modal-footer">
                        <button type="submit" form="addFriendForm" value="AddFriend" class="btn btn-outline-dark">Add Friend</button>
                    </div>
                </div>
            </div>
        </div>
        <!--CREATE GROUP POPUP -->
        <div class="modal fade" id="CreateGroup" data-bs-backdrop="static" data-bs-keyboard="false" tabindex="-1" aria-labelledby="CreateGroupLabel" aria-hidden="true">
            <div class="modal-dialog modal-dialog-centered">
                <div class="modal-content">
                    <div class="modal-header">
                        <h5 class="modal-title oswald" id="CreateGroupLabel">Create New Group:</h5>
                        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                    </div>
                    <div class="modal-body">
                        <input class="form-control" type="text" placeholder="Enter Group Name" name="groupName" required>
                        <input class="form-control" type="text" placeholder="Enter Group Description" name="groupDes" required>
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-outline-dark">Confirm New Group</button>
                    </div>
                </div>
            </div>
        </div>  
        <!--MY ACCOUNT POPUP -->
        <div class="modal fade" id="MyAccount" data-bs-backdrop="static" data-bs-keyboard="false" tabindex="-1" aria-labelledby="MyAccountLabel" aria-hidden="true">
            <div class="modal-dialog modal-dialog-centered">
                <div class="modal-content">
                    <div class="modal-header">
                        <h5 class="modal-title oswald" id="MyAccountLabel">Your Details</h5>
                        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                    </div>
                    <div class="modal-body">
                        <p class="black-color">Name: <%=user.getName()%></p>
                        <p class="black-color">Surname: <%=user.getSurname()%></p>
                        <p class="black-color">Email: <%=user.getEmail()%></p>
                        <p class="black-color">ID Number: <%=user.getUserID()%></p>
                        <p class="black-color">Cell Phone Number: <%=user.getCellNumber()%></p>
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-outline-dark"  data-bs-dismiss="modal" data-bs-toggle="modal" data-bs-target="#ChangeEmail">Change Email</button>
                        <button type="button" class="btn btn-outline-dark"  data-bs-dismiss="modal" data-bs-toggle="modal" data-bs-target="#ChangeCell">Change Cell Number</button>
                        <button type="button" class="btn btn-outline-dark"  data-bs-dismiss="modal" data-bs-toggle="modal" data-bs-target="#ChangePassword">Change Password</button>
                    </div>
                </div>
            </div>
        </div>
        <!--CHANGE PASSWORD POPUP -->
        <div class="modal fade" id="ChangePassword" data-bs-backdrop="static" data-bs-keyboard="false" tabindex="-1" aria-labelledby="ChangePasswordLabel" aria-hidden="true">
            <div class="modal-dialog modal-dialog-centered">
                <div class="modal-content">
                    <form action="UserServlet" method="POST">
                        <div class="modal-header">
                            <h5 class="modal-title oswald" id="ChangePassword">Change Password:</h5>
                            <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                        </div>
                        <div class="modal-body">
                            <input class="form-control" type="password" placeholder="Enter new Password" name="password" required>
                            <input class="form-control" type="password" placeholder="Confirm new Password" name="passwordConfirm" required>
                            <input type="hidden" name ="type" value="password">
                            <input type="hidden" name ="userID" value="<%=user.getUserID()%>">
                        </div>
                        <div class="modal-footer">
                            <button  name ="action" value="updateUser" type="submit" class="btn btn-outline-dark">Confirm New Password</button>
                        </div>
                    </form>
                </div>
            </div>
        </div>
        <!--CHANGE EMAIL POPUP -->
        <div class="modal fade" id="ChangeEmail" data-bs-backdrop="static" data-bs-keyboard="false" tabindex="-1" aria-labelledby="ChangeEmailLabel" aria-hidden="true">
            <div class="modal-dialog modal-dialog-centered">
                <div class="modal-content">
                    <div class="modal-header">
                        <h5 class="modal-title oswald" id="ChangeEmailLabel">Change Email:</h5>
                        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                    </div>
                    <form action="UserServlet" method="POST">
                        <div class="modal-body">
                            <input class="form-control" type="email" placeholder="Enter new Email" name="email" required>
                            <input type="hidden" name ="type" value="email">
                            <input type="hidden" name ="userID" value="<%=user.getUserID()%>">
                        </div>
                        <div class="modal-footer">
                            <button name ="action" value="updateUser" type="submit" class="btn btn-outline-dark">Confirm New Email</button>
                        </div>
                   </form>
                </div>
            </div>
        </div>
        <!--CHANGE CELLNUM POPUP -->
        <div class="modal fade" id="ChangeCell" data-bs-backdrop="static" data-bs-keyboard="false" tabindex="-1" aria-labelledby="ChangeCellLabel" aria-hidden="true">
            <div class="modal-dialog modal-dialog-centered">
                <div class="modal-content">
                    <div class="modal-header">
                        <h5 class="modal-title oswald" id="ChangeCellLabel">Change Cell Number:</h5>
                        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                    </div>
                    <form action="UserServlet" method="POST">
                        <div class="modal-body">
                            <input class="form-control" type="text" placeholder="Enter new Cell Number" name="cellNum" required>
                            <input type="hidden" name ="type" value="cellNum">
                            <input type="hidden" name ="userID" value="<%=user.getUserID()%>">
                        </div>
                        <div class="modal-footer">
                            <button name ="action" value="updateUser" type="submit" class="btn btn-outline-dark">Confirm New Cell Number</button>
                        </div>
                    </form>
                </div>
            </div>
        </div>
        <!--FRIEND DETAILS POPUP -->
        <%for(int i = 0; i<user.getFriendList().size();i++){%>
            <div class="modal fade" id="FriendDetails<%=i%>" data-bs-backdrop="static" data-bs-keyboard="false" tabindex="-1" aria-labelledby="FriendDetailsLabel" aria-hidden="true">
                <div class="modal-dialog modal-dialog-centered">
                    <div class="modal-content">
                        <div class="modal-header">
                            <h5 class="modal-title oswald" id="FriendDetailsLabel"><b>NAME<%=user.getFriendList().get(i).getName()%></b>SURNAME<%=user.getFriendList().get(i).getSurname()%></h5>
                            <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                        </div>
                        <div class="modal-body">
                            <p class="black-color">Email: <%=user.getFriendList().get(i).getEmail()%></p>
                            <p class="black-color">Cell Phone Number: <%=user.getFriendList().get(i).getCellNumber()%></p>
                        </div>
                        <div class="modal-footer justify-content-center">
                            <button type="button" class="btn btn-outline-dark">Remove Friend</button>
                        </div>
                    </div>
                </div>
            </div>
        <%}%>
        <!--LEARN MORE POST POPUP -->
        <%for(int j=0;j<post.size();j++){%>
            <div class="modal fade" id="PostLearnMore<%=j%>" data-bs-backdrop="static" data-bs-keyboard="false" tabindex="-1" aria-labelledby="staticBackdropLabel" aria-hidden="true">
                <div class="modal-dialog modal-dialog-centered">
                    <div class="modal-content">
                        <div class="modal-header">
                            <h5 class="modal-title oswald" id="staticBackdropLabel"><%=dao.getPost(post.get(j).getPostID()).getTitle()%></h5>
                            <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                        </div>
                        <div class="modal-body">
                            <div>
                                <p class="black-color"><%=dao.getPost(post.get(j).getPostID()).getInformation()%><</p>
                            </div>
                            <!-- ANOTHER PERSONS COMMENT -->
                            <div class="oswald border border-dark border-3 overflow-auto post-comment-block">
                                <div class="comment-bubble oswald border border-dark border-2">
                                    <div class="person-name"><%=dao.getUser(dao.getPost(post.get(j).getPostID()).getOwnerID()).getName()%> <%=dao.getUser(dao.getPost(postID).getOwnerID()).getSurname()%></div>
                                    <div class="chat-message overflow-visible"><%=dao.getPost(postID).getInformation()%></div>
                                    <div class="message-date-time"><%=format.format(dao.getPost(postID).getUploadTime())%></div>
                                    <button class="btn btn-outline-dark flag-btn">Flag Comment</button>
                                </div>
                                <!-- YOUR COMMENT -->
                                <!--<%ArrayList<Comment> comments = dao.getCommentsForPost(postID);%>
                                <%for(int i = 0; i <comments.size();i++){%>
                                <div class="comment-bubble oswald border border-dark border-2">
                                    <div class="person-name" align="right">YOUR NAME:</div>
                                    <div class="chat-message overflow-visible" align="right">THE MESSAGE</div>
                                    <div class="message-date-time" align="right">12 August 2021 </div>
                                </div>
                                <%}%>-->
                            </div>
                        </div>
                        <div class="modal-footer">
                            <input type="text">
                            <button type="submit" form="addFriendForm" value="AddFriend" class="btn btn-outline-dark">Post Comment</button>
                        </div>
                    </div>
                </div>
            </div>
        <%}%>

    </body>
</html>
