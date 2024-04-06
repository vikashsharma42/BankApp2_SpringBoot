const TransactType = document.querySelector("#transact-type")
const PaymentCard = document.querySelector(".payment-card")
const TransferCard = document.querySelector(".transfer-card")
const DepositCard = document.querySelector(".deposit-card")
const WithdrawCard = document.querySelector(".withdraw-card")

TransactType.addEventListener("change", () => {

    switch (TransactType.value){
       /* case "payment":
            PaymentCard.style.display = "block";
            PaymentCard.nextElementSibling.style.display = "none";
            DepositCard.style.display = "none";
            WithdrawCard.style.display = "none";
        break; */
        case "transfer":
            TransferCard.previousElementSibling.style.display = "none"
            TransferCard.style.display = "block"
            TransferCard.nextElementSibling.style.display = "none";
            WithdrawCard.style.display = "none";
       break;
        case "deposit":
            DepositCard.previousElementSibling.style.display = "none"
            DepositCard.style.display = "block"
            DepositCard.nextElementSibling.style.display = "none";
            PaymentCard.style.display = "none";
        break;

        case "withdraw":
            WithdrawCard.previousElementSibling.style.display = "none"
            WithdrawCard.style.display = "block"
            PaymentCard.style.display = "none";
            TransferCard.style.display = "none";
        break;

    }
})

function matchPassword(){
	const password=document.getElementById("password").value
	const cpassword=document.getElementById("cpass").value
	const msg=document.getElementById("msg")
	if(password!==cpassword){
		msg.innerText = "confirm password and password does not match "
		msg.style.color = "red"
		return false
	}
	else if(password.length<=5){
		msg.innerText = "Entered password must be greater then 5"
		msg.style.color = "red"
		return false
	}
	else{
		msg.innerText = ""
		return true
	}
}

function startTypingEffect() {
    const textToType = document.getElementById("typingText").innerText;
    const typingTextContainer = document.getElementById("typingTextContainer");
    let index = 0;
    function typeText() {
        typingTextContainer.innerHTML += textToType[index];
        index++;
        if (index < textToType.length) {
            setTimeout(typeText, 50); 
        }
         else {
            setTimeout(function () {
                typingTextContainer.innerHTML = "";
                index = 0;
                typeText();
            }, 2000); 
        }
    }
    typingTextContainer.innerHTML = "";
    typeText();
}

function isNumberKey(evt) {
    const charCode = (evt.which) ? evt.which : evt.keyCode;
    return !(charCode > 31 && (charCode < 48 || charCode > 57));
}

function checkWithdrawLimit(amount)
{
	if(amount.value>30000)
 	{
		document.getElementById("warning").style.color="red"
		document.getElementById("warning").innerHTML="sorry !!!! maximum 30000 allowed per transaction";
 	    document.getElementById("withdraw").disabled=true;
 	}
	else
	{
		document.getElementById("warning").innerHTML="";
		document.getElementById("withdraw").disabled=false;     
	}
}
function checkDepositeLimit(amount)
{
	if(amount.value>40000)
 	{
		document.getElementById("msg").style.color="red"
		document.getElementById("msg").innerHTML="sorry !!!! maximum 40000 allowed per transaction";
 	    document.getElementById("deposit").disabled=true;
 	}
	else
	{
		document.getElementById("msg").innerHTML="";
		document.getElementById("deposit").disabled=false;     
	}
}
function checkTransferLimit(amount)
{
	if(amount.value>40000)
 	{
		document.getElementById("info").style.color="red"
		document.getElementById("info").innerHTML="sorry !!!! maximum 45000 allow to transfer";
 	    document.getElementById("deposit").disabled=true;
 	}
	else
	{
		document.getElementById("info").innerHTML="";
		document.getElementById("deposit").disabled=false;     
	}
}