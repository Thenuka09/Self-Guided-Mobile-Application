<?php include('header_main.php'); ?>
<?php require_once('./includes/connection.php'); ?>

<?php

// if (!isset($_SESSION['adminId'])) {
//     header('Location: index.php');
// }

?>

<?php 

    if(isset($_POST['addAdminSubmit'])){

        $errors = array();

        $name = mysqli_real_escape_string($connection , $_POST['adminName']);
        $phoneNumber = mysqli_real_escape_string($connection , $_POST['adminPhoneNumber']);
        $email = mysqli_real_escape_string($connection , $_POST['adminEmail']);
        $password = mysqli_real_escape_string($connection , $_POST['adminPassword']);

        $hased_password = password_hash($password, PASSWORD_DEFAULT);

        $query = "INSERT INTO counselor ( name, phone_number , email, password) VALUES (
            ('{$name}')  , ('{$phoneNumber}'), ('{$email }'),('{$hased_password}')
        )";

        $result = mysqli_query($connection , $query);

        if($result){
            //query insert successfull 
            header('Location: manage_main_counselor.php?insert_query = true');
        }else{
            $errors[] = 'faild to add record';
        }

    }
?>

<section class="addAdminSection">

<div class="adminDiv">

    <form action="add_main_counselor.php" method="post" class="adminForm" id="signinForm">

        <fieldset class="adminFieldset">

            <legend class="adminLegend">
                <h1>Add Counselor</h1>
            </legend>

            <p class="adminInput">
                <label for="signinFirstName">Name :</label>
                <input type="text" name="adminName" id="adminName" placeholder="enter name" autofocus>
                <small>error message</small>
            </p>

            <p class="adminInput">
                <label for="signinPhoneNumber">Phone Number :</label>
                <input type="text" name="adminPhoneNumber" id="adminPhoneNumber" placeholder="ex : 07*-***-****">
                <small>error message</small>
            </p>

            <p class="adminInput">
                <label for="signinEmail">Email :</label>
                <input type="text" name="adminEmail" id="adminEmail" placeholder="enter email">
                <small>error message</small>
            </p>


            <p class="adminInput">
                <label for="signinPassword">Password :</label>
                <input type="password" name="adminPassword" id="adminPassword" placeholder="new password">
                <small>error message</small>
            </p>

            <p class="adminInput">
                <label for="signinCheckPassword">Re-enter Password : </label>
                <input type="password" name="adminReEnterPassword" id="adminCheckPassword" placeholder="re-enter new password">
                <small>error message</small>
            </p>

            <p class="addAdminSubmit">
                <button type="submit" name="addAdminSubmit" id="signinSubmit" class="addAdminSubmit">+ Add Counselor</button>
            </p>

        </fieldset>

    </form>

</div>

</section>

</body>

<script src="./Js/script.js"></script>
</html>
<?php mysqli_close($connection); ?>