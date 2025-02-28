// //login validation part start

// const form = document.getElementById('formLogin');
// const username = document.getElementById('username');
// const password = document.getElementById('password');

// form.addEventListener('submit', function(event){

//     //event.preventDefault();

//     checkInputsValues();

//     if(isFormValid()==true){
//         form.submit();
//     }else{
//         event.preventDefault();
//     }

// });


// function isFormValid(){

//     const inputContainers = form.querySelectorAll('.input'); // all input fields inside the 'para' classes 
    
//     var result=true;
//     inputContainers.forEach((container)=>{
//         if(container.classList.contains('error')){ // add error class
//             result = false;
//         }
//     });

//     return result;
// }
 
// function checkInputsValues(){
//     const usernameValue = username.value.trim();
//     const passwordValue = password.value.trim();

//     //username validation part
//     if(usernameValue === ""){
//         errorSet(username , 'user name can not blank' );
//     }else if(!isEmail(usernameValue)){
//         errorSet(username , 'not a valid email' );
//     }else{
//         successSet(username);
//     }

//     //password validation

//     if(passwordValue === ""){
//         errorSet(password , 'password can not blank' );
//     }else{
//         successSet(password);
//     }
// }

// function errorSet(input , message){

//     const formControl = input.parentElement;
//     const small = formControl.querySelector('small');

//     //add error message inside to the message
//     small.innerText = message;

//     //add error class
//     formControl.className = ('input error');
// }

// function successSet(input){
//     const formControl = input.parentElement;
//     formControl.className = ('input success');
// }

// //email validation part2

//     function isEmail(username){
    
//         var UserEnterEmail = document.getElementById('username').value;
//         var atpos=UserEnterEmail.indexOf("@");
//         var dotpos=UserEnterEmail.lastIndexOf(".");
    
//         if(atpos<1 || dotpos<atpos+2 || dotpos+2>=UserEnterEmail.length){
//             return false;
//         }else{
//             return true;
//         }
//     }

// login validation part start
const form = document.getElementById('formLogin');
const username = document.getElementById('username');
const password = document.getElementById('password');

form.addEventListener('submit', function (event) {

    checkInputsValues();

    if (isFormValid()) {
        form.submit();
    } else {
        event.preventDefault();
    }

});

function isFormValid() {
    const inputContainers = form.querySelectorAll('.adminLoginInput');
    let result = true;
    inputContainers.forEach((container) => {
        if (container.classList.contains('error')) {
            result = false;
        }
    });
    return result;
}

function checkInputsValues() {
    const usernameValue = username.value.trim();
    const passwordValue = password.value.trim();

    // username validation
    if (usernameValue === "") {
        errorSet(username, 'Username cannot be blank');
    } else if (!isEmail(usernameValue)) {
        errorSet(username, 'Not a valid email');
    } else {
        successSet(username);
    }

    // password validation
    if (passwordValue === "") {
        errorSet(password, 'Password cannot be blank');
    } else {
        successSet(password);
    }
}

function errorSet(input, message) {
    const formControl = input.parentElement;
    const small = formControl.querySelector('small');

    // Add error message inside the small element
    small.innerText = message;

    // Add error class
    formControl.classList.add('error');
    formControl.classList.remove('success');
}

function successSet(input) {
    const formControl = input.parentElement;
    formControl.classList.add('success');
    formControl.classList.remove('error');
}

// Email validation part
function isEmail(email) {
    const atpos = email.indexOf("@");
    const dotpos = email.lastIndexOf(".");
    return !(atpos < 1 || dotpos < atpos + 2 || dotpos + 2 >= email.length);
}
