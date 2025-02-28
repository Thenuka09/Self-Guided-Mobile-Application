<?php session_start(); ?>
<?php require_once('./includes/connection.php'); ?>
<?php include('header.php'); ?>
<?php include('anxiety_levels.php'); ?>

<?php

// check the user is logedin
if (!isset($_SESSION['adminId'])) {
    header('Location: index.php');
}

?>
<?php
if (isset($_GET['id'])) {

    $studentId = $_GET['id'];

    $query = "UPDATE students SET is_deleted_anxiety = 1 WHERE  id = $studentId ";

    $result = mysqli_query($connection, $query);

    if ($result) {
        //Not available the food
        header('Location: anxiety_minimal.php?order_completed');
    } else {
        header('Location: anxiety_minimal.php?order_not_completed');
    }
}

?>

<section class="minimalAnxietyDetails">

    <div class="minimalAnxietyTable">

        <table class="details" border="1">

            <thead>
                <tr>
                    <th>Name</th>
                    <th>Email</th>
                    <th>Faculty</th>
                    <th>Phone Number</th>
                    <th>Student ID</th>
                    <th>User Say</th>
                    <th>Anxiety Marks</th>
                    <th>Remove Student</th>

                </tr>
            </thead>
            <tbody>

                <?php

                // Get the logged-in counselor ID
                $counselorId = $_SESSION['adminId'];

                //$query = "SELECT * FROM students WHERE consent = 'Yes' AND marks BETWEEN 0 AND 4 AND is_deleted_anxiety = 0";
                // Ensure only counselor 3 sees students with add_counselor = 2 
                // and counselor 8 sees students with add_counselor = 3
                if ($counselorId == 1) {
                    $query = "SELECT * FROM students WHERE consent = 'Yes' AND marks BETWEEN 0 AND 4 
                                AND is_deleted_anxiety = 0 AND add_counselor = 2";
                } elseif ($counselorId == 2) {
                    $query = "SELECT * FROM students WHERE consent = 'Yes' AND marks BETWEEN 0 AND 4 
                                AND is_deleted_anxiety = 0 AND add_counselor = 3";
                } else {
                    // If an unknown user logs in, return an empty result
                    $query = "SELECT * FROM students WHERE 1=0";
                }

                $result = mysqli_query($connection, $query);

                if (mysqli_num_rows($result) > 0) {

                    foreach ($result as $row) {

                ?>
                        <tr>

                            <td><?= $row['name']; ?></td>
                            <td><?= $row['email']; ?></td>
                            <td><?= $row['faculty']; ?></td>
                            <td><?= $row['phone_number']; ?></td>
                            <td><?= $row['student_id']; ?></td>
                            <td><?= $row['user_say']; ?></td>
                            <td><?= $row['marks']; ?></td>
                            <td> <a href="anxiety_minimal.php?id=<?= $row['id']; ?>" onclick="return confirm('Are you sure delete the record?');">Remove</a> </td>

                        </tr>

                <?php
                    }
                }

                ?>

            </tbody>

        </table>

    </div>

</section>

</body>

<script src="./Js/script.js"></script>

</html>
<?php mysqli_close($connection); ?>