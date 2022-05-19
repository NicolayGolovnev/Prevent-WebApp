var counterForChildrens = 0
var counterForQuestion = 0
var counterForAnswers = 0
var counterForKeys = 0
$('#forWeightArg').val(1)

function generateNewChildQuiz() {
    const insert = `<div class="form-group row child-${counterForChildrens}" style="flex-wrap: nowrap">
                    <div class="col-md-6" style="width: auto">
                        <input hidden type="text" name="" value="">
                        <select class="custom-select" name="">
                            <option selected="selected" disabled value="none">-</option>
                        </select>
                    </div>
                    <button class="btn col-md-1" type="button" style="width: 50px" 
                    onclick="deleteChildrenQuizSelect(${counterForChildrens})">
                        <ion-icon name="trash-outline"></ion-icon>
                    </button>
                </div>`

    $(".children-group").append(insert)

    // добавления списка сгенерированных всех опросников с бека
    $.ajax({
        type: 'GET',
        url: "/ajax/getListQuizzesForSelect",
        dataType: 'html',
        success: function (response) {
            let select = $('.child-' + ' select');
            select.empty();
            select.replaceWith(response);
        }
    });

    counterForChildrens += 1
}

function deleteChildrenQuizSelect(childId) {
    const childRow = '.child-' + childId
    $(childRow).remove()
}

function generateNewQuestion() {
    const insert = `<div class="question-${counterForQuestion}">
            <input hidden type="text" name="" id="idQuestion" value="0">
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
            <input hidden type="text" name="" id="idAnswer" value="0">
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
                        <input hidden type="text" name="" id="idKey" value="0">
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
        $(child + ' input')[0].name = 'childQuizzes[' + index + ']'
        $(child + ' input')[0].id = 'childQuizzes' + index
        $(child + ' select')[0].name = 'childQuizzes[' + index + '].childQuiz'
        $(child + ' select')[0].id = 'childQuizzes' + index + '.childQuiz'
        // console.log('Измененное имя у дочернего опроса: ' + $(child + ' select')[0].name);
    });

    // для вопросов
    $('.question-group').children().each(function (index, elem) {
        const question = '.' + elem.classList[0]
        $(question + ' #idQuestion')[0].name = 'questions[' + index + ']'
        $(question + ' #idQuestion')[0].id = 'questions' + index
        $(question + ' #numQuestion')[0].name = 'questions[' + index + '].numQuestion'
        $(question + ' #numQuestion')[0].id = 'questions' + index + '.numQuestion'
        $(question + ' #questionContent')[0].name = 'questions[' + index + '].content'
        $(question + ' #questionContent')[0].id = 'questions' + index + '.content'
        $(question + ' #questionWeight')[0].name = 'questions[' + index + '].weight'
        $(question + ' #questionWeight')[0].id = 'questions' + index + '.weight'
        $(question + ' .answer-group').children().each(function (index2, elem2) {
            const answer = '.' + elem2.classList[2]
            $(answer + ' #idAnswer')[0].name = 'questions[' + index + '].answers[' + index2 + ']'
            $(answer + ' #idAnswer')[0].id = 'questions' + index + '.answers' + index2
            $(answer + ' #answerContent')[0].name = 'questions[' + index + '].answers[' + index2 + '].content'
            $(answer + ' #answerContent')[0].id = 'questions' + index + '.answers' + index2 + '.content'
            $(answer + ' #answerWeight')[0].name = 'questions[' + index + '].answers[' + index2 + '].weight'
            $(answer + ' #answerWeight')[0].id = 'questions' + index + '.answers' + index2 + '.weight'
        })
    });

    // для ключей
    $('.keys-group').children().each(function (index, elem) {
        const key = '.' + elem.classList[0]
        $(key + ' #idKey')[0].name = 'keys[' + index + ']'
        $(key + ' #idKey')[0].id = 'keys' + index
        $(key + ' select')[0].name = 'keys[' + index + '].gender'
        $(key + ' select')[0].id = 'keys' + index + '.gender'
        $(key + ' #minArg')[0].name = 'keys[' + index + '].minArg'
        $(key + ' #minArg')[0].id = 'keys' + index + '.minArg'
        $(key + ' #maxArg')[0].name = 'keys[' + index + '].maxArg'
        $(key + ' #maxArg')[0].id = 'keys' + index + '.maxArg'
        $(key + ' #resultArg')[0].name = 'keys[' + index + '].resultArg'
        $(key + ' #resultArg')[0].id = 'keys' + index + '.resultArg'
    });
}