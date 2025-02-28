<?php 

    session_start();

    // Unset specific session variables
    unset($_SESSION['adminId']);
    unset($_SESSION['counselorName']);

    // Redirect to login page with a logout message
    header('Location: index.php?logout=yes');
    exit;

?>