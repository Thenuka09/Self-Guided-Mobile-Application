<?php session_start()?>

<div class="name_depression">

    <img src="./images/hi.png" width="40px" alt="" class="hi">
    <?php 
        if (isset($_SESSION['counselorName'])) {
            echo "Hi, ",$_SESSION['counselorName']; 
        } else {
            echo "Hi, Guest"; // Fallback text if session is not set
        }
    ?>
</div>

<div class="depresionLevels">
    <a href="depression_severe.php" class="depressionSevere">Severe Depression</a>
    <a href="depression_moderate_severe.php" class="depressionModerateSevere">Moderate Severe Depression</a>
    <a href="depression_moderate.php" class="depresionModerate">Moderate Depression</a>
    <a href="depression_mild.php" class="depresionMild">Mild Depression</a>
    <a href="depression_minimal.php" class="depressionMinimal">Minimal Depression</a>
</div>

<!-- Vertical Divider -->
<div class="depressionDivider"></div>



