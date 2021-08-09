<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <link rel="stylesheet" href="css/bootstrap.min.css">
        <link rel="stylesheet" href="css/CodeCloud.css">
        <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Oswald">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.0/jquery.min.js"></script>
        <script src="js/bootstrap.min.js"></script>
        <title>Code Cloud</title>
    </head>
    <body>
        <h1 class="text-center"><b>Code</b>Cloud</h1>

        <!--NAVIGATION MENU -->
        <nav class="navbar navbar-expand-lg navbar-light bg-light">
            <div class="container-fluid">
                <a class="navbar-brand"><b>VZAP</b>Alumni</a>
                <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
                    <span class="navbar-toggler-icon"></span>
                </button>
                <div class="collapse navbar-collapse" id="navbarSupportedContent">
                    <ul class="navbar-nav me-auto mb-2 mb-lg-0">
                        <li class="nav-item">
                            <a class="nav-link" aria-current="page" href="HomePage.jsp">Home</a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" href="Inbox.jsp">Inbox</a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link active" href="CodeCloud.jsp">Code Cloud</a>
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
                            <a class="nav-link" href="#">Logout</a>
                        </li>
                    </ul>
                </div>
            </div>
        </nav>
        <!-- BACKGROUND IMAGE -->
        <img class="img-height" src="images/bruce-mars-xj8qrWvuOEs-unsplash.jpg">

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
                        <p class="black-color">Name:</p>
                        <p class="black-color">Surname:</p>
                        <p class="black-color">Email:</p>
                        <p class="black-color">ID Number:</p>
                        <p class="black-color">Cell Phone Number:</p>
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
                    <div class="modal-header">
                        <h5 class="modal-title oswald" id="ChangePassword">Change Password:</h5>
                        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                    </div>
                    <div class="modal-body">
                        <input class="form-control" type="password" placeholder="Enter new Password" name="password" required>
                        <input class="form-control" type="password" placeholder="Confirm new Password" name="passwordConfirm" required>
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-outline-dark">Confirm New Password</button>
                    </div>
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
                    <div class="modal-body">
                        <input class="form-control" type="email" placeholder="Enter new Email" name="email" required>
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-outline-dark">Confirm New Email</button>
                    </div>
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
                    <div class="modal-body">
                        <input class="form-control" type="text" placeholder="Enter new Cell Number" name="cellNum" required>
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-outline-dark">Confirm New Cell Number</button>
                    </div>
                </div>
            </div>
        </div>

        <!-- ASKED QUESTIONS LIST -->
        <h1 class="h1"><b>UNSOLVED</b>QUESTIONS</h1>
        <div class="row chat-list overflow-auto oswald">
            <div >
                <div class="list-group" id="list-tab" role="tablist">
                    <a class="list-group-item list-group-item-action active" id="personchat1" data-bs-toggle="list" href="#chat1" role="tab" aria-controls="list-home">Tomcat Problems<span class="badge bg-primary rounded-pill">14</span></a>
                    <a class="list-group-item list-group-item-action" id="personchat2" data-bs-toggle="list" href="#chat2" role="tab" aria-controls="list-profile">Null Pointer<span class="badge bg-primary rounded-pill">1</span></a>
                    <a class="list-group-item list-group-item-action" id="personchat3" data-bs-toggle="list" href="#chat3" role="tab" aria-controls="list-messages">Netbeans Problems<span class="badge bg-primary rounded-pill">12</span></a>
                    <a class="list-group-item list-group-item-action" id="personchat4" data-bs-toggle="list" href="#chat4" role="tab" aria-controls="list-settings">Runtime Error<span class="badge bg-primary rounded-pill">11</span></a>
                </div>
            </div>
        </div>

        <!-- YOUR QUESTIONS LIST -->
        <h1 class="friends-header"><b>YOUR</b>QUESTIONS</h1>
        <div class="row friends-list overflow-auto oswald">
            <div >
                <div class="list-group" id="friends-list" role="tablist">
                    <a class="list-group-item list-group-item-action" id="personchat1" data-bs-toggle="list" href="#question1" role="tab" aria-controls="list-home">Question 1<span class="badge bg-primary rounded-pill">12</span></a>
                    <a class="list-group-item list-group-item-action" id="personchat2" data-bs-toggle="list" href="#question2" role="#question2" aria-controls="list-profile">Question 2<span class="badge bg-primary rounded-pill">1</span></a>
                    <a class="list-group-item list-group-item-action" id="personchat3" data-bs-toggle="list" href="#question3" role="#question3" aria-controls="list-messages">Question 3<span class="badge bg-primary rounded-pill">13</span></a>
                    <a class="list-group-item list-group-item-action" id="personchat4" data-bs-toggle="list" href="#question4" role="tab" aria-controls="list-settings">Question 4<span class="badge bg-primary rounded-pill">5</span></a>
                </div>
            </div>
        </div>

        <!-- QUESTIONS CHAT BOX AREA -->
        <div class="chat-box oswald border border-white border-3 overflow-auto tab-content" id="nav-tabContent">
            <!-- UNSOLVED QUESTIONS -->
            <!-- A QUESTION CHAT -->
            <div class="tab-pane fade show active" id="chat1" role="tabpanel" aria-labelledby="personchat1">
                <!-- A RECEIVED MESSAGE -->
                <div class="chat-bubble oswald border border-white border-2">
                    <div class="person-name">PERSON NAME:</div>
                    <div class="chat-message overflow-visible">THE MESSAGE</div>
                    <div class="message-date-time">12 August 2021 </div>
                    <button class="btn btn-outline-dark flag-btn">Flag Message</button>
                </div>
                <!-- A SENT MESSAGE -->
                <div class="chat-bubble oswald border border-white border-2">
                    <div class="person-name" align="right">YOUR NAME:</div>
                    <div class="chat-message overflow-visible" align="right">THE MESSAGE</div>
                    <div class="message-date-time" align="right">12 August 2021 </div>
                </div>
            </div>
            <!-- A QUESTION CHAT -->
            <div class="tab-pane fade" id="chat2" role="tabpanel" aria-labelledby="personchat2">
                <!-- A RECEIVED MESSAGE -->
                <div class="chat-bubble oswald border border-white border-2">
                    <div class="person-name">PERSON NAME:</div>
                    <div class="chat-message overflow-visible">THE MESSAGE</div>
                    <div class="message-date-time">12 August 2021 </div>
                    <button class="btn btn-outline-dark flag-btn">Flag Message</button>
                </div>
                <!-- A SENT MESSAGE -->
                <div class="chat-bubble oswald border border-white border-2">
                    <div class="person-name" align="right">YOUR NAME:</div>
                    <div class="chat-message overflow-visible" align="right">THE MESSAGE</div>
                    <div class="message-date-time" align="right">12 August 2021 </div>
                </div>
            </div>
            <!-- A QUESTION CHAT -->
            <div class="tab-pane fade" id="chat3" role="tabpanel" aria-labelledby="personchat3">
                <!-- A RECEIVED MESSAGE -->
                <div class="chat-bubble oswald border border-white border-2">
                    <div class="person-name">PERSON NAME:</div>
                    <div class="chat-message overflow-visible">THE MESSAGE</div>
                    <div class="message-date-time">12 August 2021 </div>
                    <button class="btn btn-outline-dark flag-btn">Flag Message</button>
                </div>
                <!-- A SENT MESSAGE -->
                <div class="chat-bubble oswald border border-white border-2">
                    <div class="person-name" align="right">YOUR NAME:</div>
                    <div class="chat-message overflow-visible" align="right">THE MESSAGE</div>
                    <div class="message-date-time" align="right">12 August 2021 </div>
                </div>
            </div>
            <!-- A QUESTION CHAT -->
            <div class="tab-pane fade" id="chat4" role="tabpanel" aria-labelledby="personchat4">
                <!-- A RECEIVED MESSAGE -->
                <div class="chat-bubble oswald border border-white border-2">
                    <div class="person-name">PERSON NAME:</div>
                    <div class="chat-message overflow-visible">THE MESSAGE</div>
                    <div class="message-date-time">12 August 2021 </div>
                    <button class="btn btn-outline-dark flag-btn">Flag Message</button>
                </div>
                <!-- A SENT MESSAGE -->
                <div class="chat-bubble oswald border border-white border-2">
                    <div class="person-name" align="right">YOUR NAME:</div>
                    <div class="chat-message overflow-visible" align="right">THE MESSAGE</div>
                    <div class="message-date-time" align="right">12 August 2021 </div>
                </div>
            </div>
            <!-- YOUR QUESTIONS -->
            <!-- A QUESTION CHAT -->
            <div class="tab-pane fade" id="question1" role="tabpanel" aria-labelledby="personchat4">
                <!-- A RECEIVED MESSAGE -->
                <div class="chat-bubble oswald border border-white border-2">
                    <div class="person-name">PERSON NAME:</div>
                    <div class="chat-message overflow-visible">THE MESSAGE</div>
                    <div class="message-date-time">12 August 2021 </div>
                    <button class="btn btn-outline-dark flag-btn">Flag Message</button>
                </div>
                <!-- A SENT MESSAGE -->
                <div class="chat-bubble oswald border border-white border-2">
                    <div class="person-name" align="right">YOUR NAME:</div>
                    <div class="chat-message overflow-visible" align="right">THE MESSAGE</div>
                    <div class="message-date-time" align="right">12 August 2021 </div>
                </div>
                <!-- QUESTION SOLVED BUTTON ALWAYS PUT AT BOTTOM OF CHAT-->
                <button type="button" class="btn btn-outline-dark solved-button">Question Solved</button>
            </div>
            <!-- A QUESTION CHAT -->
            <div class="tab-pane fade" id="question2" role="tabpanel" aria-labelledby="personchat4">
                <!-- A RECEIVED MESSAGE -->
                <div class="chat-bubble oswald border border-white border-2">
                    <div class="person-name">PERSON NAME:</div>
                    <div class="chat-message overflow-visible">THE MESSAGE</div>
                    <div class="message-date-time">12 August 2021 </div>
                    <button class="btn btn-outline-dark flag-btn">Flag Message</button>
                </div>
                <!-- A SENT MESSAGE -->
                <div class="chat-bubble oswald border border-white border-2">
                    <div class="person-name" align="right">YOUR NAME:</div>
                    <div class="chat-message overflow-visible" align="right">THE MESSAGE</div>
                    <div class="message-date-time" align="right">12 August 2021 </div>
                </div>
                <!-- QUESTION SOLVED BUTTON ALWAYS PUT AT BOTTOM OF CHAT-->
                <button type="button" class="btn btn-outline-dark solved-button">Question Solved</button>
            </div>
            <!-- A QUESTION CHAT -->
            <div class="tab-pane fade" id="question3" role="tabpanel" aria-labelledby="personchat4">
                <!-- A RECEIVED MESSAGE -->
                <div class="chat-bubble oswald border border-white border-2">
                    <div class="person-name">PERSON NAME:</div>
                    <div class="chat-message overflow-visible">THE MESSAGE</div>
                    <div class="message-date-time">12 August 2021 </div>
                    <button class="btn btn-outline-dark flag-btn">Flag Message</button>
                </div>
                <!-- A SENT MESSAGE -->
                <div class="chat-bubble oswald border border-white border-2">
                    <div class="person-name" align="right">YOUR NAME:</div>
                    <div class="chat-message overflow-visible" align="right">THE MESSAGE</div>
                    <div class="message-date-time" align="right">12 August 2021 </div>
                </div>
                <!-- QUESTION SOLVED BUTTON ALWAYS PUT AT BOTTOM OF CHAT-->
                <button type="button" class="btn btn-outline-dark solved-button">Question Solved</button>
            </div>
            <!-- A QUESTION CHAT -->
            <div class="tab-pane fade" id="question4" role="tabpanel" aria-labelledby="personchat4">
                <!-- A RECEIVED MESSAGE -->
                <div class="chat-bubble oswald border border-white border-2">
                    <div class="person-name">PERSON NAME:</div>
                    <div class="chat-message overflow-visible">THE MESSAGE</div>
                    <div class="message-date-time">12 August 2021 </div>
                    <button class="btn btn-outline-dark flag-btn">Flag Message</button>
                </div>
                <!-- A SENT MESSAGE -->
                <div class="chat-bubble oswald border border-white border-2">
                    <div class="person-name" align="right">YOUR NAME:</div>
                    <div class="chat-message overflow-visible" align="right">THE MESSAGE</div>
                    <div class="message-date-time" align="right">12 August 2021 </div>
                </div>
                <!-- QUESTION SOLVED BUTTON ALWAYS PUT AT BOTTOM OF CHAT-->
                <button type="button" class="btn btn-outline-dark solved-button">Question Solved</button>
            </div>
        </div>    

        <!-- INPUT TO SEND A MESSAGE -->
        <form  action="MessageServlet" method="POST">
            <div class=" row justify-content-center send-message-block">
                <input class="form-control message-input" type="text" placeholder="Say Something nice :)" name="messageArea">
                <input class="form-control send-message-button" type="submit" value="Send Message" name="sendMessage">
            </div>
        </form>
        
        <!-- ASK NEW QUESTION BUTTON -->
        <button type="button" class="btn btn-outline-light new-question-btn" data-bs-toggle="modal" data-bs-target="#NewQuestion">Ask New Question</button>

        <!-- ASK NEW QUESTION POPUP -->
        <div class="modal fade" id="NewQuestion" data-bs-backdrop="static" data-bs-keyboard="false" tabindex="-1" aria-labelledby="NewQuestionLabel" aria-hidden="true">
            <div class="modal-dialog modal-dialog-centered">
                <div class="modal-content">
                    <div class="modal-header">
                        <h5 class="modal-title oswald" id="NewQuestionLabel">Ask New Question</h5>
                        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                    </div>
                    <div class="modal-body">
                        <input class="form-control" type="text" placeholder="Enter Question Header" name="cellNum" required>
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-outline-dark">Confirm New Question</button>
                    </div>
                </div>
            </div>
        </div>
    </body>
</html>