function addNewSmartPhone() {
    let producer = $('#producer').val();
    let model = $('#model').val();
    let price = $('#price').val();
    let newSmartPhone = {
        producer: producer,
        model: model,
        price: price
    };
    $.ajax({
        headers: {
            'Accept': 'application/json',
            'Content-type': 'application/json'
        },
        type: "POST",
        data: JSON.stringify(newSmartPhone),
        url: "http://localhost:8080/api/smart-phone",
        success: console.log("success")
    });
    event.preventDefault();
}

function successHandler() {
    $.ajax({
        type: "GET",
        url: "http://localhost:8080/api/smart-phone",
        success: function (data) {
            let content = '<table id="display-list" border="1"><tr>\n' +
                ' <th>Producer</th>\n' +
                ' <th>Model</th>\n' +
                ' <th>Price</th>\n' +
                ' <th>Delete</th>\n' +
                ' </tr>';
            for (let i = 0; i < data.length; i++) {
                content += getSmartPhone(data[i]);
            }
            content += "</table>";
            $('#smartphoneList').html(content);
        }
    });
}

function getSmartPhone(smartphone) {
    return `<tr>
                <td >${smartphone.producer}</td>
                <td >${smartphone.model}</td>
                <td >${smartphone.price}</td>` +
        `       <td class="btn"><button class="deleteSmartphone" onclick="deleteSmartphone(${smartphone.id})">Delete</button></td></tr>`;
}
function deleteSmartphone(id) {
    $.ajax({
        type: "DELETE",
        url: `http://localhost:8080/api/smart-phone/${id}`,
        success: successHandler
    });
}