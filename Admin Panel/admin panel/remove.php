<?php require_once('./includes/connection.php'); ?>

<?php 
    if(isset($_GET['id'])){

    $studentId = $_GET['id'];

    $query = "UPDATE students SET is_deleted_student = 1 WHERE  id = $studentId ";

    $result= mysqli_query($connection , $query);

    if($result){
        //Not available the record
        header('Location: all_details.php?yes');
        
    }else{
        header('Location: all_details.php?no');
    }
    }

?>

<?php mysqli_close($connection); ?>