<?php 

    session_start();

    // Unset specific session variables
    unset($_SESSION['counselorID']);
    unset($_SESSION['mainCounselorName']);

    // Redirect to login page with a logout message
    header('Location: index.php?logout=yes');
    exit;

?>