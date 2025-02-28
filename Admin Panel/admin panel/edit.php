<?php session_start(); ?>
<?php include('header.php'); ?>
<?php require_once('./includes/connection.php'); ?>

<?php
if (!isset($_SESSION['adminId'])) {
    header('Location: index.php');
    exit();
}
?>

<?php
if (isset($_POST['editAdminSubmit'])) {
    $errors = array();

    $id = $_POST['id'];
    $name = mysqli_real_escape_string($connection, $_POST['adminName']);
    $phoneNumber = mysqli_real_escape_string($connection, $_POST['adminPhoneNumber']);
    $email = mysqli_real_escape_string($connection, $_POST['adminEmail']);
    $password = mysqli_real_escape_string($connection, $_POST['adminPassword']);

    if (!empty($password)) {
        $hashed_password = password_hash($password, PASSWORD_DEFAULT);
        $query = "UPDATE assistentcounselor SET 
                    name = '$name', 
                    phone_number = '$phoneNumber', 
                    email = '$email', 
                    password = '$hashed_password' 
                  WHERE id = '$id'";
    } else {
        $query = "UPDATE assistentcounselor SET 
                    name = '$name', 
                    phone_number = '$phoneNumber', 
                    email = '$email' 
                  WHERE id = '$id'";
    }

    $result = mysqli_query($connection, $query);

    if ($result) {
        header('Location: profile.php?status=success');
        exit();
    } else {
        header('Location: profile.php?status=error');
        $errors[] = 'Failed to update record';
        exit();
    }
}
?>

<section class="addAdminSection">
    <div class="adminDiv">
        <?php
        if (isset($_GET['id'])) {
            $id = $_GET['id'];
            $query = "SELECT * FROM assistentcounselor WHERE id = '$id'";
            $counselorRun = mysqli_query($connection, $query);
            
            if (mysqli_num_rows($counselorRun) > 0) {
                $counselor = mysqli_fetch_assoc($counselorRun);
        ?>
                <form action="edit.php" method="post" class="adminForm" id="signinForm">
                    <fieldset class="adminFieldset">




                        <legend class="adminLegend">
                            <h1>Edit Details</h1>
                        </legend>

                        <input type="hidden" name="id" value="<?=$counselor['id'];?>">

                        <p class="adminInput">
                            <label for="signinFirstName">Name :</label>
                            <input type="text" name="adminName" id="adminName" placeholder="Enter name" value="<?=$counselor['name'];?>" required>
                        </p>

                        <p class="adminInput">
                            <label for="signinPhoneNumber">Phone Number :</label>
                            <input type="text" name="adminPhoneNumber" id="adminPhoneNumber" placeholder="07*-***-****" value="<?=$counselor['phone_number'];?>" required>
                        </p>

                        <p class="adminInput">
                            <label for="signinEmail">Email :</label>
                            <input type="email" name="adminEmail" id="adminEmail" placeholder="Enter email" value="<?=$counselor['email'];?>" required>
                        </p>

                        <p class="adminInput">
                            <label for="signinPassword">Password :</label>
                            <input type="password" name="adminPassword" id="adminPassword" placeholder="New password (if you want)">
                        </p>

                        <p class="adminInput">
                            <label for="signinCheckPassword">Re-enter Password : </label>
                            <input type="password" name="adminReEnterPassword" id="adminCheckPassword" placeholder="re-enter new password">
                            <small>error message</small>
                        </p>

                        <p class="addAdminSubmit">
                            <button type="submit" name="editAdminSubmit" id="signinSubmit" class="addAdminSubmit">Edit</button>
                        </p>
                    </fieldset>
                </form>

        <?php
            } else {
                echo "<h1>No record found</h1>";
            }
        }
        ?>
    </div>

</body>
</section>

<script src="./Js/script.js"></script>
</html>
<?php mysqli_close($connection); ?>
