<?php session_start()?>

<div class="name">

    <img src="./images/hi.png" width="40px" alt="" class="hi">
    <?php 
        if (isset($_SESSION['counselorName'])) {
            echo "Hi, ",$_SESSION['counselorName']; 
        } else {
            echo "Hi, Guest"; // Fallback text if session is not set
        }
    ?>
</div>


<div class="levels">
    <a href="anxiety_severe.php" class="severe">Severe Anxiety</a>
    <a href="anxiety_moderate.php" class="moderate">Moderate Anxiety</a>
    <a href="anxiety_mild.php" class="mild">Mild Anxiety</a>
    <a href="anxiety_minimal.php" class="minimal">Minimal Anxiety</a>
</div>

<!-- Vertical Divider -->
<div class="divider"></div>



