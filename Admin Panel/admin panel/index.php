<?php
session_start();
require_once('./includes/connection.php');


if (isset($_POST['loginSubmitBtn'])) {    

    $error = array();

    // Check if username and password are provided
    if (!isset($_POST['usernameLogin']) || strlen(trim($_POST['usernameLogin'])) < 1) {
        $error[] = 'Username is missing or invalid';
    }

    if (!isset($_POST['passwordLogin']) || strlen(trim($_POST['passwordLogin'])) < 1) {
        $error[] = 'Password is missing or invalid';
    }

    if (empty($error)) {
        // Save username and password into variables
        $username = mysqli_real_escape_string($connection, $_POST['usernameLogin']);
        $password = $_POST['passwordLogin']; // Do not escape the password here

        // Prepare the database query to fetch the user
        $query = "SELECT * FROM assistentcounselor WHERE email = '{$username}' LIMIT 1";

        $result = mysqli_query($connection, $query);

        if ($result) {
            // Query successful
            if (mysqli_num_rows($result) == 1) {
                // Valid user found
                $user = mysqli_fetch_assoc($result);
                
                
                // Verify the password
                if (password_verify($password, $user['password'])) {
                    $_SESSION['adminId'] = $user['id'];
                    $_SESSION['counselorName'] = $user['name'];

                    // Redirect to the anxiety_severe.php file
                    header('Location: anxiety_severe.php');
                    exit;
                } else {
                    $error[] = 'Invalid username or password';
                }
            } else {
                $error[] = 'Invalid username or password';
            }
        } else {
            $error[] = 'Database query failed';
        }
    }
}
?>


<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="./css/login.css">
    <title>Log in</title>
</head>

<body>

    <section class="formLoginSection">

        <div class="login">

            <form action="index.php" method="post" class="formLogin" id="formLogin">

                <fieldset class="loginFieldset">

                    <legend>
                        <h1>Log in</h1>
                    </legend>


                    <?php

                    if (isset($error) && !empty($error)) {
                        echo '<p class="errorMessage">Username and password does not match</p>';
                    }

                    ?>

                    <!-- check the user successfully logout from the system-->
                    <?php 
                
                    if(isset($_GET['logout'])){
                        echo '<p class="successMessage">Successfully loged out from the system</p>';
                    }
            
                    ?>

                    <p class="adminLoginInput">
                        <label for="">Email</label>
                        <input type="text" placeholder="enter your email address" name="usernameLogin" autofocus id="username">
                        <small>error message</small>
                    </p>

                    <p class="adminLoginInput">
                        <label for="">password</label>
                        <input type="password" placeholder="enter password" name="passwordLogin" id="password">
                        <small>error message</small>
                    </p>

                    <p class="loginSubmit">
                        <button type="submit" name="loginSubmitBtn" class="adminLoginSubmitBtn">Submit</button>
                    </p>

                    <div class="loginAsMain">
                        <a href="login_main.php">Login as Main Counselor</a>
                    </div>
                    

                </fieldset>

            </form>

        </div>

    </section>

</body>

<script src="./Js/login.js"></script>

</html>
<?php mysqli_close($connection); ?>