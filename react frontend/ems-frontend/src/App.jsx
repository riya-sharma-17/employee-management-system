
import './App.css'
import HelloWorld from './HelloWorld'
import ListEmployeeComponents from './components/ListEmployeeComponents'
import HeaderComponent from './components/HeaderComponent'
import FooterComponent from './components/FooterComponent'
import { BrowserRouter, Route, Routes} from 'react-router-dom'
import EmployeeComponent  from './components/EmployeeComponent'
import ListDepartmentComponents from './components/ListDepartmentComponents'
import DepartmentComponent from './components/DepartmentComponent'
function App() {
  return (
    <>
      <BrowserRouter>
        <HeaderComponent/>
        <Routes>
          {/* // http://localhost:3000 */}
          <Route path='/' element={<ListEmployeeComponents/>}></Route>
          {/* // http://localhost:3000/employees */}
          <Route path='/employees' element={<ListEmployeeComponents/>}></Route>
          {/* // http://localhost:3000/add-employee */}
          <Route path='/add-employee' element={<EmployeeComponent/>}></Route>
          {/* // http://localhost:3000/edit-employee */}
          <Route path='/edit-employee/:id' element={<EmployeeComponent/>}></Route>

          {/* // http://localhost:3000/departments */}
          <Route path='/departments' element={<ListDepartmentComponents/>}></Route>

          {/* // http://localhost:3000/add-department */}
          <Route path='/add-department' element={<DepartmentComponent/>}></Route>

          <Route path='/edit-department/:id' element = { <DepartmentComponent />}></Route>

        </Routes>
        <FooterComponent/>
      </BrowserRouter>
        
    </>
  )
}

export default App
