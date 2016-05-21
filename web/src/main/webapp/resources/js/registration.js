var loadFile = function(event) {
    var output = document.getElementById('avatar');
    output.src = URL.createObjectURL(event.target.files[0]);
  };