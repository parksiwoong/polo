let xhr = (method, url, callback, fallback, formData) => {
    let xhr = new XMLHttpRequest();
    xhr.open(method, url);
    xhr.onreadystatechange = () => {
        if (xhr.readyState === XMLHttpRequest.DONE) {
            if (xhr.status >= 200 && xhr.status < 300) {
                callback(xhr.responseText);
            } else {
                fallback();
            }
        }
    };
    if (typeof (formData) !== 'undefined') {
        xhr.send(formData);
    } else {
        xhr.send();
    }
}

let addForm = window.document.body.querySelector("#add");
addForm.onsubmit = function () {
    let nameRegex = new RegExp("^([0-9a-zA-Z가-힣 ]{2,100})$");
    let colorRegex = new RegExp("^([a-zA-Z가-힣 ]{1,100})$");
    let sizeRegex = new RegExp("^([0-9A-Z]{1,5})$");

    if (addForm.elements["name"].value === "") {
        alert("상품을 입력해주세요.");
        addForm.elements["name"].focus();
        return false;
    } else if (!nameRegex.test(addForm.elements["name"].value)) {
        alert("상품명형식이 틀립니다.");
        addForm.elements["name"].focus();
        return false;
    } else if (addForm.elements["price"].value === "") {
        alert("가격을 입력해주세요.");
        addForm.elements["price"].focus();
        return false;
    } else if (addForm.elements["color"].value === "") {
        alert("색상을 입력해주세요.");
        addForm.elements["color"].focus();
        return false;
    } else if (!colorRegex.test(addForm.elements["color"].value)) {
        alert("색상형식이 틀립니다.");
        addForm.elements["color"].focus();
        return false;
    } else if (addForm.elements["size"].value === "") {
        alert("사이즈 입력해주세요.");
        addForm.elements["size"].focus();
        return false;
    } else if (!sizeRegex.test(addForm.elements["size"].value)) {
        alert("사이즈형식이 틀립니다.");
        addForm.elements["size"].focus();
        return false;

    } else {
        let inputName = window.document.body.querySelector("#name");
        let inputPrice = window.document.body.querySelector("#price");
        let inputColor = window.document.body.querySelector("#color");
        let inputSize = window.document.body.querySelector("#size");
        alert("등록성공하였습니다.");
//
// let inputValue = [inputName.value, inputPrice.value, inputColor.value, inputSize.value];

        let formData = new FormData();

        formData.append("name", inputName.value)
        formData.append("price", inputPrice.value)
        formData.append("color", inputColor.value)
        formData.append("size", inputSize.value)

        function callback(response) {
            let result = JSON.parse(response);
            if (result['result'] === 'not_allowed') {
                alert('권한 없음');
            }
        }

        function fallback() {

        }

        xhr("POST", "/add", callback, fallback, formData);
        return false;
    }
}
