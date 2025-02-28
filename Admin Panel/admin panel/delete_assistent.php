<?php require_once('./includes/connection.php'); ?>

<?php 

    if(isset($_GET['id'])){

        $id = $_GET['id'];

        $query = "DELETE FROM assistentcounselor WHERE id = $id ";

        $result= mysqli_query($connection , $query);

        if($result){
            //Not available the food
            header('Location: manage_assistent_counselor.php?counselor_deleted');
        }else{
            header('Location: manage_assistent_counselor.php?counselor_Not_deleted');
        }
    }

?>


<?php mysqli_close($connection); ?>