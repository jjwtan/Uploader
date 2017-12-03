($(document).ready(function() {
    var dropzone = document.getElementById('dropzone');

    if(dropzone) {
        var upload = function(files) {
            var formData = new FormData();
            var MAX_FILES = 3;
            var x;

            for(x = 0; x < files.length && x < MAX_FILES; x=x+1) {
                console.log(files[x]);
                formData.append('files[]', files[x]);
            }

            submitData(formData);
        }

        var submitData = function(formData) {
             $.ajax({
                type: "POST",
                encryptType: 'multipart/form-data',
                url: "/upload",
                data: formData,
                processData: false,
                contentType: false,
                cache: false,
                timeout: 600000,
                success: function (data) {
                    $("#result").text(data);
                    console.log("SUCCESS : ", data);
                },
                error: function (e) {
                    $("#result").text(e.responseText);
                    console.log("ERROR : ", e);
                }
             });
         }

        dropzone.ondrop = function(e) {
            // prevent the default action which is to open the dropped file
            e.preventDefault();
            this.className = 'dropzone';
            upload(e.dataTransfer.files);
        }

        dropzone.ondragover = function() {
            this.className = 'dropzone dragover';
            return false;
        }

        dropzone.ondragleave = function() {
            this.className = 'dropzone';
            return false;
        }
    }
}()));