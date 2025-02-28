<?php require_once('./includes/connection.php'); ?>
<?php include('header_main.php'); ?>

<div class="manage_counselors">

    <div class="container">
        <div class="manage_main_counselor">
            <a href="manage_main_counselor.php">Manage Main Counselors</a>
        </div>

        <div class="manage_assistent_counselor">
            <a href="manage_assistent_counselor.php">Manage Assistent Counselors</a>
        </div>
    </div>

    <div class="logout">
        <a href="logout_main.php" onclick="return confirm('Are you sure to Logout?');">Logout</a>
    </div>

</div>

</body>

<script src="./Js/script.js"></script>
</html>
<?php mysqli_close($connection); ?>