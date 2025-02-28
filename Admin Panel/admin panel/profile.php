<?php session_start(); ?>
<?php include('header.php'); ?>
<?php require_once('./includes/connection.php'); ?>

<?php

if (!isset($_SESSION['adminId'])) {
    header('Location: index.php');
}

$adminId = $_SESSION['adminId']; // Get logged-in counselor's ID


?>

<div class="profile">

    <div class="userTable">

        <table class="columns" border="1">

            <thead>
                <tr>
                    <th>Name</th>
                    <th>Email</th>
                    <th>Phone Number</th>

                </tr>
            </thead>
            <tbody>

                <?php

                $query = "SELECT * FROM assistentcounselor WHERE id = $adminId";
                $result = mysqli_query($connection, $query);

                if (mysqli_num_rows($result) > 0) {

                    foreach ($result as $row) {

                ?>
                        <tr>

                            <td><?= $row['name']; ?></td>
                            <td><?= $row['email']; ?></td>
                            <td><?= $row['phone_number']; ?></td>
                            <td> <a class="edit" href="edit.php?id=<?= $row['id']; ?>" onclick="return confirm('Are you sure edit the record?');">edit</a> </td>

                        </tr>

                <?php
                    }
                }

                ?>

            </tbody>

        </table>
        <a class="logout" href="logout.php" onclick="return confirm('Do you want to logout?');">Logout</a>
    </div>
    <?php
    if (isset($_GET['status']) && $_GET['status'] == 'success') {
        echo '<div class="alert alert-success" id="success-alert">Admin details updated successfully!</div>';
    } elseif (isset($_GET['status']) && $_GET['status'] == 'error') {
        echo '<div class="alert alert-danger" id="error-alert">Failed to update admin details. Please try again.</div>';
    }
    ?>
</div>

</body>

<script src="./Js/script.js"></script>

<script>
    setTimeout(function() {
        let successAlert = document.getElementById("success-alert");
        let errorAlert = document.getElementById("error-alert");

        if (successAlert) {
            successAlert.style.transition = "opacity 0.5s";
            successAlert.style.opacity = "0";
            setTimeout(() => successAlert.style.display = "none", 500);
        }

        if (errorAlert) {
            errorAlert.style.transition = "opacity 0.5s";
            errorAlert.style.opacity = "0";
            setTimeout(() => errorAlert.style.display = "none", 500);
        }
    }, 1000); // 10 seconds
</script>

</html>
<?php mysqli_close($connection); ?>