var counterForChildrens = 1
var counterForQuestion = 1
var counterForAnswers = 1
$('#forWeightArg').val(1)

function generateNewChildQuiz() {
    const insert = `<div class="form-group row child-${counterForChildrens}" style="flex-wrap: nowrap">
                    <div class="col-md-6" style="width: auto">
                        <select class="custom-select" name="children-quiz" id="childrenQuiz">
                            <option selected value="none">-</option>
                            <option value="1">Большой функциональный опросник PLS</option>
                            <option value="2">Функция желудка</option>
                            <option value="3">Воспаление желудка</option>
                        </select>
                    </div>
                    <button class="btn col-md-1" type="button" style="width: 50px" 
                    onclick="deleteChildrenQuizSelect(${counterForChildrens})">
                        <ion-icon name="trash-outline"></ion-icon>
                    </button>
                </div>`
    counterForChildrens += 1

    $(".children-group").append(insert)
}

function deleteChildrenQuizSelect(childId) {
    const childRow = '.child-' + childId
    $(childRow).remove()
}

function generateNewQuestion() {
    const insert = `<div class="question-${counterForQuestion}">
            <div class="form-group row justify-content-end" style="flex-wrap: nowrap">
                <button type="button" onclick="deleteQuestion(${counterForQuestion})" class="btn col-md-1">
                    <ion-icon name="trash-outline"></ion-icon>
                </button>
            </div>
            <div class="form-group row justify-content-end">
                <label for="forNumQuestion" class="col-md-3 col-form-label">Номер вопроса</label>
                <div class="col-md-8">
                    <input type="text" class="form-control" id="forNumQuestion" placeholder="Введите номер вопроса"
                           name="numQuestion">
                </div>
            </div>
            <div class="form-group row justify-content-end">
                <label for="forQuestion" class="col-md-3 col-form-label">Формулировка вопроса</label>
                <div class="col-md-8">
                    <input type="text" class="form-control" id="forQuestion" placeholder="Введите формулировку"
                           name="content">
                </div>
            </div>
            <div class="form-group row justify-content-end">
                <label for="forWeightQuestion" class="col-md-3 col-form-label">Вес вопроса</label>
                <div class="col-md-8">
                    <input type="text" class="form-control" id="forWeightQuestion" placeholder="Введите вес вопроса"
                           name="weightArg">
                </div>
            </div>
            <!-- Блок ответов для этого вопроса -->
            <hr>
            <div class="form-group">
                <label>Ответы</label>
                <div class="form-group answer-group">

                </div>
            </div>
            <div class="form-group row justify-content-end">
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
    const insert = `<div class="answer-${counterForAnswers}">
            <div class="form-group row justify-content-end" style="flex-wrap: nowrap">
                <button type="button" onclick="deleteAnswer(${questionId}, ${counterForAnswers})" class="btn col-md-1">
                    <ion-icon name="trash-outline"></ion-icon>
                </button>
            </div>
            <div class="form-group row justify-content-end">
                <label for="forAnswerContent" class="col-md-3 col-form-label">Ответ</label>
                <div class="col-md-8">
                    <input type="text" class="form-control" id="forAnswerContent" placeholder="Введите формулировку"
                           name="content">
                </div>
            </div>
            <div class="form-group row justify-content-end">
                <label for="forAnswerWeight" class="col-md-3 col-form-label">Вес ответа</label>
                <div class="col-md-8">
                    <input type="text" class="form-control" id="forAnswerWeight" placeholder="Введите вес ответа"
                           name="weightArg">
                </div>
            </div>
        </div>`
    counterForAnswers += 1

    const question = '.question-' + questionId + ' .answer-group'
    $(question).append(insert)
}

function deleteAnswer(questionId, answerId) {
    const answer = '.question-' + questionId + ' .answer-' + answerId
    $(answer).remove()
}
