<c:forEach var="course" items="${courses}">
  <p>${course.name} by ${course.instructor} (${course.credits} credits)</p>
  <form action="register/${course.courseId}" method="post">
    <input type="submit" value="Register" />
  </form>
</c:forEach>