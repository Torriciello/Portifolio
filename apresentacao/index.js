const modal = document.getElementById('contact-modal');
const btns = document.querySelectorAll('.btn-contact');
const closeBtn = document.querySelector('.close-btn');

btns.forEach(function(btn){
  btn.addEventListener('click', function(){
    modal.style.display = 'flex';
  });
});

closeBtn.addEventListener('click', function(){
  modal.style.display = 'none';
});

window.addEventListener('click', function(e){
  if(e.target === modal){
    modal.style.display = 'none';
  }
});

const texts = ["Fullstack Developer Jr", " Data Scientist Student"];
let i = 0;
const dynamicText = document.querySelector('.dynamic-text');

setInterval(function(){
  dynamicText.textContent = texts[i];
  i = (i + 1) % texts.length;
}, 2000);