const btnElement= document.querySelector('button');
const ulElement = document.querySelector('ul');
const inputElement = document.querySelector('input')

btnElement.addEventListener('click',()=>{
    const inputValue = inputElement.value;
    const newLi = document.createElement('li');
    newLi.textContent = inputValue;
    ulElement.appendChild(newLi);
    inputElement.value=""
})