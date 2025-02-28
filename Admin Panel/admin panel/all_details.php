<?php session_start(); ?>
<?php require_once('./includes/connection.php'); ?>
<?php include('header_main.php'); ?>

<?php

// check the user is logedin
if (!isset($_SESSION['counselorID'])) {
    //header('Location: all_details.php');
}

?>

<div class="allStudentsDetails">

    <div class="all_studets_table">

        <table class="all_columns" border="1">

            <div class="main_counselor">

                <img src="./images/hi.png" width="40px" alt="" class="hi">
                <?php
                if (isset($_SESSION['mainCounselorName'])) {
                    echo "Hi, ", $_SESSION['mainCounselorName'];
                } else {
                    echo "Hi, Guest"; // Fallback text if session is not set
                }
                ?>
            </div>

            <thead>
                <tr>
                    <th>Name</th>
                    <th>faculty</th>
                    <th>Phone Number</th>
                    <th>Email</th>
                    <th>Anxiety Level</th>
                    <th>Depression Level</th>
                    <th>Assigned Counselor</th>
                    <th></th>

                </tr>
            </thead>
            <tbody>

                <?php

                function getAnxietyLevel($score)
                {
                    if (is_null($score) || $score === NULL) return '<span class="unknown">-</span>';
                    if ($score >= 0 && $score <= 4) return '<span class="minimal">Minimal Anxiety</span>';
                    if ($score >= 5 && $score <= 9) return '<span class="mild">Mild Anxiety</span>';
                    if ($score >= 10 && $score <= 14) return '<span class="moderate">Moderate Anxiety</span>';
                    if ($score >= 15 && $score <= 21) return '<span class="severe">Severe Anxiety</span>';
                    return  '<span class="unknown">Unknown</span>'; // Fallback in case of unexpected values
                }

                function getDepressionLevel($score)
                {
                    if (is_null($score) || $score === NULL) return '<span class="unknown">-</span>';
                    if ($score >= 0 && $score <= 4) return '<span class="Minimal_Depression">Minimal Depression</span>';
                    if ($score >= 5 && $score <= 9) return '<span class="Mild_Depression">Mild Depression</span>';
                    if ($score >= 10 && $score <= 14) return '<span class="Moderate_Depression">Moderate Depression</span>';
                    if ($score >= 15 && $score <= 19) return '<span class="Moderately_severe_depression">Moderately Severe Depression</span>';
                    if ($score >= 20 && $score <= 27) return '<span class="Severe_depression">Severe Depression</span>';
                    return '<span class="unknown">-</span>';
                }

                function assignCounselor($score)
                {
                    if ($score == 0) return '<span class="notAssigned">Not Assigned</span>';
                    if ($score == 1) return '<span class="assignToMainCounselor">Assign to Main Counselor</span>';
                    if ($score == 2) return '<span class="assignToAssistent1">Assign to Assistent 1</span>';
                    if ($score == 3) return '<span class="assignToAssistent2">Assign to Assistent 2</span>';
                    return '<span class="notAssigned">Not Assigned</span>';
                }


                $query = "SELECT * FROM students WHERE consent = 'Yes' AND is_deleted_student = 0";
                $result = mysqli_query($connection, $query);

                if (mysqli_num_rows($result) > 0) {

                    foreach ($result as $row) {

                ?>
                        <tr>

                            <td><?= $row['name']; ?></td>
                            <td><?= $row['faculty']; ?></td>
                            <td><?= $row['phone_number']; ?></td>
                            <td><?= $row['email']; ?></td>
                            <td><?= getAnxietyLevel($row['marks']); ?></td>
                            <td><?= getDepressionLevel($row['dep_marks']); ?></td>
                            <td><?= assignCounselor($row['add_counselor']); ?></td>
                            <td> <a class="delete" href="remove.php?id=<?= $row['id']; ?>" onclick="return confirm('Are you sure remove the record?');">Remove</a> </td>

                        </tr>

                <?php
                    }
                }

                ?>

            </tbody>

        </table>
    </div>
</div>

</body>

<style>
    .minimal {
        color: #24d175;
        font-weight: 500;
    }

    .mild {
        color: rgb(146, 201, 65);
        font-weight: 500;
    }

    .moderate {
        color: #fdb456;
        font-weight: 500;
    }

    .severe {
        color: #fc2a2a;
        font-weight: 500;
    }

    .Minimal_Depression {
        color: #24d175;
        font-weight: 500;
    }

    .Mild_Depression {
        color: #ffec16;
        font-weight: 500;
    }

    .Moderate_Depression {
        color: rgb(253, 208, 86);
        font-weight: 500;
    }

    .Moderately_severe_depression {
        color: rgb(252, 146, 104);
        font-weight: 500;
    }

    .Severe_depression {
        color: #ff1919;
        font-weight: 500;
    }

    .notAssigned {
        color: black;
    }

    .assignToMainCounselor {
        color: rgb(44, 184, 109);
        font-weight: bold;
    }

    .assignToAssistent1 {
        color: rgb(46, 159, 224);
        font-weight: bold;
    }

    .assignToAssistent2 {
        color: #e94be1;
        font-weight: bold;
    }

    .unknown {
        color: black;
        font-weight: 500;
    }
</style>

<script src="./Js/script.js"></script>

</html>
<?php mysqli_close($connection); ?>