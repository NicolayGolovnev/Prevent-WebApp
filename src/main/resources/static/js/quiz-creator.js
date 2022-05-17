var counterForChildrens = 0
var counterForQuestion = 0
var counterForAnswers = 0
var counterForKeys = 0
$('#forWeightArg').val(1)

function generateNewChildQuiz() {
    const insert = `<div class="form-group row child-${counterForChildrens}" style="flex-wrap: nowrap">
                    <div class="col-md-6" style="width: auto">
                        <select class="custom-select" name="">
                            <option selected="selected" disabled value="none">-</option>
                        </select>
                    </div>
                    <button class="btn col-md-1" type="button" style="width: 50px" 
                    onclick="deleteChildrenQuizSelect(${counterForChildrens})">
                        <ion-icon name="trash-outline"></ion-icon>
                    </button>
                </div>`
    counterForChildrens += 1

    $(".children-group").append(insert)

    // добавления списка сгенерированных всех опросников с бека
    $.ajax({
        type: 'GET',
        url: "/ajax/getListQuizzesForSelect",
        dataType: 'html'
    }).done( function (response) {
        let select = $('.children-group select');
        select.empty();
        select.replaceWith(response);
    });
}

function deleteChildrenQuizSelect(childId) {
    const childRow = '.child-' + childId
    $(childRow).remove()
}

function generateNewQuestion() {
    const insert = `<div class="question-${counterForQuestion}">
            <div class="form-group row justify-content-between" style="flex-wrap: nowrap">
                <div class="col-md-4 row justify-content-start align-items-center">
                    <span class="mx-3">Вопрос №</span>
                    <input type="text" class="form-control" placeholder="№" style="width: 55px"
                           name="" id="numQuestion" value="">
                </div>
                <button type="button" class="btn col-md-1 justify-content-end" style="width: 50px;"
                        onclick="deleteQuestion(${counterForQuestion})">
                    <ion-icon name="trash-outline"></ion-icon>
                </button>
            </div>
            <div class="form-group row">
                <div class="col-md-10 my-1">
                    <input type="text" class="form-control" placeholder="Формулировка вопроса"
                           name="" id="questionContent" value="">
                </div>
                <div class="col-md-2 my-1">
                    <input type="text" class="form-control" placeholder="Вес"
                           name=""
                           id="questionWeight" value="">
                </div>
            </div>
            <!-- Блок ответов для этого вопроса -->
            <hr>
            <div class="form-group">
                <label>Ответы</label>
                <div class="form-group answer-group">

                </div>
            </div>
            <div class="row justify-content-end">
                <button type="button" onclick="generateNewAnswerForQuestion(${counterForQuestion})"
                        class="btn btn-success col-md-5 m-2">Добавить ответ</button>
            </div>
        </div>`
    counterForQuestion += 1

    $('.question-group').append(insert)
}

function deleteQuestion(questionId) {
    const question = '.question-' + questionId
    $(question).remove()
}

function generateNewAnswerForQuestion(questionId) {
    const insert = `<div class="form-group row answer-${counterForAnswers}" style="flex-wrap: nowrap">
            <div class="col-md-5" style="width: auto">
                <input type="text" class="form-control" list="answersList" placeholder="Формулировка ответа"
                       name="" id="answerContent" value="">
                <datalist id="answersList">
                    <option value="Нет или редко"></option>
                    <option value="Иногда"></option>
                    <option value="Часто"></option>
                    <option value="Очень часто"></option>
                    <option value="Да"></option>
                    <option value="Нет"></option>
                </datalist>
            </div>
            <div class="col-md-2" style="width: 80px;">
                <input type="text" class="form-control" placeholder="Вес"
                       name="" id="answerWeight" value="">
            </div>
            <button type="button" class="btn col-md-1" style="width: 50px"
                    onclick="deleteAnswer(${questionId}, ${counterForAnswers})">
                <ion-icon name="trash-outline"></ion-icon>
            </button>
        </div>`
    counterForAnswers += 1

    const question = '.question-' + questionId + ' .answer-group'
    $(question).append(insert)
}

function deleteAnswer(questionId, answerId) {
    const answer = '.question-' + questionId + ' .answer-' + answerId
    $(answer).remove()
}

function generateNewKey() {
    const insert = `<div class="key-${counterForKeys}">
                        <div class="row justify-content-between m-auto" style="flex-wrap: nowrap">
                            <div class="col-md-8">
                                <div class="row justify-content-between">
                                    <select class="custom-select col-md-6 mr-auto my-1" name="" id="gender">
                                        <option selected disabled value="">Гендер опросника</option>
                                        <option value="Мужчина">Мужчина</option>
                                        <option value="Женщина">Женщина</option>
                                        <option value="Любой">Любой</option>
                                    </select>
                                    <div class="col-md-5 align-items-center mr-auto">
                                        <div class="row justify-content-center align-items-center my-1" style="flex-wrap: nowrap">
                                            <input type="text" class="form-control col-md-5" placeholder="MIN"
                                                   name="" id="minArg" value="">
                                            <span class="col-md-2" style="text-align: center">-</span>
                                            <input type="text" class="form-control col-md-5" placeholder="MAX"
                                                   name="" id="maxArg" value="">
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <button class="btn col-md-1 justify-content-end my-1" type="button" style="width: 50px"
                                    onclick="deleteKeyQuiz(${counterForKeys})">
                                <ion-icon name="trash-outline"></ion-icon>
                            </button>
                        </div>
                        <div class="row m-auto my-1" style="flex-wrap: nowrap">
                            <input type="text" class="form-control col-md-12" placeholder="Результат тестирования"
                                   name="" id="resultArg" value="">
                        </div>
                    </div>`

    counterForKeys += 1
    $(".keys-group").append(insert)
}

function deleteKeyQuiz(keyId) {
    const keyRow = '.key-' + keyId
    $(keyRow).remove()
}

function renameData() {
    // все дочерние опросники
    $('.children-group').children().each(function (index, elem) {
        const child = '.' + elem.classList[2]
        $(child + ' select')[0].name = 'childQuizzes[' + index + '].childQuiz'
        console.log('Измененное имя: ' + $(child + ' select')[0].name);
    });

    // для вопросов
    $('.question-group').children().each(function (index, elem) {
        const question = '.' + elem.classList[0]
        $(question + ' #numQuestion')[0].name = 'questions[' + index + '].numQuestion'
        console.log('Измененное имя у # вопроса: ' + $(question + ' #numQuestion')[0].name);
        $(question + ' #questionContent')[0].name = 'questions[' + index + '].content'
        console.log('Измененное имя у content вопроса: ' + $(question + ' #questionContent')[0].name);
        $(question + ' #questionWeight')[0].name = 'questions[' + index + '].weight'
        console.log('Измененное имя у weight вопроса: ' + $(question + ' #questionWeight')[0].name);
        $(question + ' .answer-group').children().each(function (index2, elem2) {
            const answer = '.' + elem2.classList[2]
            $(answer + ' #answerContent')[0].name = 'questions[' + index + '].answers[' + index2 + '].content'
            console.log('Измененное имя у content ответа: ' + $(answer + ' #answerContent')[0].name);
            $(answer + ' #answerWeight')[0].name = 'questions[' + index + '].answers[' + index2 + '].weight'
            console.log('Измененное имя у weight ответа: ' + $(answer + ' #answerWeight')[0].name);
        })
    });

    // для ключей
    $('.keys-group').children().each(function (index, elem) {
        const key = '.' + elem.classList[0]
        $(key + ' select')[0].name = 'keys[' + index + '].gender'
        console.log('Измененное имя у gender ключа: ' + $(key + ' select')[0].name);
        $(key + ' #minArg')[0].name = 'keys[' + index + '].minArg'
        console.log('Измененное имя у minArg ключа: ' + $(key + ' #minArg')[0].name);
        $(key + ' #maxArg')[0].name = 'keys[' + index + '].maxArg'
        console.log('Измененное имя у maxArg ключа: ' + $(key + ' #maxArg')[0].name);
        $(key + ' #resultArg')[0].name = 'keys[' + index + '].resultArg'
        console.log('Измененное имя у resultArg ключа: ' + $(key + ' #resultArg')[0].name);
    });
}