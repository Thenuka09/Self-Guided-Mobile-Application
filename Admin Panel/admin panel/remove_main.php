<?php require_once('./includes/connection.php'); ?>

<?php 
    if(isset($_GET['id'])){

    $studentId = $_GET['id'];

    $query = "UPDATE students SET is_deleted_student_from_main_counselor = 1 WHERE  id = $studentId ";

    $result= mysqli_query($connection , $query);

    if($result){
        //Not available the record
        header('Location: my_students.php?yes');
        
    }else{
        header('Location: my_students.php?no');
    }
    }

?>

<?php mysqli_close($connection); ?>