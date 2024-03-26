<table>
    <tr>
        <th>No</th>
        <th>Surname</th>
        <th>Name</th>
        <th>AGE</th>
        <th>City</th>
        <th>Street</th>
        <th>Building</th>
        <th>Mark</th>
        <th>Country</th>
        <th></th>
        <th></th>
        <th></th>
    </tr>

    <%for (int i = 0; i < listDto.size(); i++) {%>
    <%StudentDTO studentDTO = listDto.get(i);%>
    <%String s = String.valueOf(studentDTO.getId());%>

    <tr>
        <td><%=(i + 1)%>
        </td>
        <td><%=studentDTO.getSurname()%>
        </td>
        <td><%=studentDTO.getName()%>
        </td>
        <td><%=studentDTO.getAge()%>
        </td>
        <td><%=studentDTO.getCity()%>
        </td>
        <td><%=studentDTO.getStreet()%>
        </td>
        <td><%=studentDTO.getBuilding()%>
        </td>
        <td><%=studentDTO.getMark()%>
        </td>
        <td><%=Optional.ofNullable(studentDTO.getCountryName()).orElse("Not set")%>
        </td>

        <td>
            <form action="update" method="get">
                <input type="hidden" value="<%=s%>" name="id">
                <button class="btn btn-light" type="submit">Edit</button>
            </form>
        </td>
        <td>
            <form action="delete" method="post">
                <input type="hidden" value="<%=s%>" name="id">
                <button class="btn btn-light" type="submit">Delete</button>
            </form>
        </td>
    </tr>
    <% } %>
</table>