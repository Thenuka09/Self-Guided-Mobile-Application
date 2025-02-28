// Apply active state to header navigation links
document.querySelectorAll('.header-links a').forEach(headerLink => {
    if (headerLink.href === window.location.href) {
      headerLink.setAttribute('aria-current', 'page');
      headerLink.style.backgroundColor = '#0097b2';
      headerLink.style.color = 'white';
    }
  });
  
  // Apply active state to levels links and sync with the "Anxiety" header link
  document.querySelectorAll('.levels a').forEach(levelLink => {
    if (levelLink.href === window.location.href) {
      levelLink.setAttribute('aria-current', 'page');
      levelLink.style.fontSize = '17px';
      levelLink.style.fontWeight = 'bold';
      levelLink.style.border = '2px solid black';
  
      // Style the anxiety link in the header if it's an anxiety-related page
      const anxietyHeaderLink = document.querySelector('.header-links .anxiety');
      if (anxietyHeaderLink) {
        anxietyHeaderLink.style.backgroundColor = '#0097b2';
        anxietyHeaderLink.style.color = 'white';
      }
    }
  });

// Apply active state to depression levels links
document.querySelectorAll('.depresionLevels a').forEach(levelLink => {
  if (levelLink.href === window.location.href) {
    levelLink.setAttribute('aria-current', 'page');
    levelLink.style.fontSize = '17px';
    levelLink.style.fontWeight = 'bold';
    levelLink.style.border = '2px solid black';

    const depressionHeaderLink = document.querySelector('.header-links .depression');
    if (depressionHeaderLink) {
      depressionHeaderLink.style.backgroundColor = '#0097b2';
      depressionHeaderLink.style.color = 'white';
    }
  }
});
   
//add admins JS

const form = document.getElementById('signinForm');
const adminName = document.getElementById('adminName');
const phoneNumber = document.getElementById('adminPhoneNumber');
const email = document.getElementById('adminEmail');
const password = document.getElementById('adminPassword');
const checkPassword = document.getElementById('adminCheckPassword');

form.addEventListener('submit', function(event){

    //event.preventDefault();

    checkInputsValues();

    if(isFormValid()==true){
        form.submit();
    }else{
        event.preventDefault();
    }

});

// call the success function
success();

function isFormValid(){

    const inputContainers = form.querySelectorAll('.adminInput'); // all input fields inside the 'para' classes 
    
    var result=true;
    inputContainers.forEach((container)=>{
        if(container.classList.contains('error')){ // add error class
            result = false;
        }
    });

    return result;
}

function checkInputsValues(){

    const nameValue = adminName.value.trim();
    const phoneNumberValue = phoneNumber.value.trim();
    const emailValue = email.value.trim();
    const passwordValue = password.value.trim();
    const checkPasswordValue = checkPassword.value.trim();

    //first name validation part
    if(nameValue === ''){
        errorSet(adminName , 'first name can not blank');
    }else{
        successSet(adminName);
    }

    // phone number validation part
    if(phoneNumberValue === ''){
        errorSet(phoneNumber, 'phone number can not blank');
    }else if(isNumber(phoneNumberValue)){
        successSet(phoneNumber);
    }else{
        errorSet(phoneNumber, 'not a valid number');
    }

    //email validation part
    if(emailValue === ''){
        errorSet(email , 'email can not blank');
    }else if(!isEmail(emailValue)){
        errorSet(email , 'not a valid email , try again');
    }else{
        successSet(email);
    }

    //password validation part
    if(passwordValue === ''){
        errorSet(password , 'password can not blank');
    }else{
        successSet(password);
    }

    //password checker validaton part
    if(checkPasswordValue === ''){
        errorSet(checkPassword , 're-enter password can not blank');
    }else if(checkPasswordValue !== passwordValue){
        errorSet(checkPassword , 'password does not match');
    }else{
        successSet(checkPassword);
    }

}

function errorSet(input , message){

    const formControl = input.parentElement;
    const small = formControl.querySelector('small');

    //add error message inside the message
    small.innerText = message;

    //add error class
    formControl.className = ('adminInput error');
}

function successSet(input){
    const formControl = input.parentElement;
    formControl.className = ('adminInput success');
}

//phone number validation
function isNumber(){

    const userEnterNumber = document.getElementById('adminPhoneNumber').value;

    if(isNaN(userEnterNumber) || userEnterNumber.length>10 || userEnterNumber.length<10){
        return false;
    }else{
        return true;
    }

}

//email validation part2
function isEmail(email){
    
    var UserEnterEmail = document.getElementById('adminEmail').value;
    var atpos=UserEnterEmail.indexOf("@");
    var dotpos=UserEnterEmail.lastIndexOf(".");

    if(atpos<1 || dotpos<atpos+2 || dotpos+2>=UserEnterEmail.length){
        return false;
    }else{
        return true;
    }
}





